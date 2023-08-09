package com.example.studifyy
import CustomAdapter
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.example.studifyy.databinding.ActivityRegistrationBinding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import com.google.firebase.firestore.FirebaseFirestore
import java.util.concurrent.TimeUnit
class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding:ActivityRegistrationBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var verificationId:String
    private  val fireStore=FirebaseFirestore.getInstance()
    private val programList= mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth=FirebaseAuth.getInstance()
       binding.OTP.setOnClickListener {
           var phoneNumber=binding.PhoneNumber.text.trim().toString()
           if(phoneNumber.isNotEmpty()&&phoneNumber.length==10){
               phoneNumber="+91$phoneNumber"
               val options= PhoneAuthOptions.newBuilder(auth).setPhoneNumber(phoneNumber)
                   .setTimeout(60L,TimeUnit.SECONDS)
                   .setActivity(this)
                   .setCallbacks(callbacks)
                   .build()
               PhoneAuthProvider.verifyPhoneNumber(options)
           }
           else{
               Toast.makeText(this@RegistrationActivity,"Enter Phone Number!!!",Toast.LENGTH_SHORT).show()
           }
       }
        binding.Submit.setOnClickListener {
            val code=binding.OTPPassword.text.trim().toString()
            if (code.isNotEmpty()&&code.length==6){
                verificationOfCode(code)
            }
            else{
                Toast.makeText(this@RegistrationActivity,"Enter OTP!!!",Toast.LENGTH_SHORT).show()
            }

        }
        fetchPrograms()
        binding.ProgramsSpinner.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selectedProgram= p0?.getItemAtPosition(p2).toString()
                binding.ProgramsSpinner.setPopupBackgroundResource(R.color.blue)
                offlineData(selectedProgram)
                openActivity(selectedProgram)

            }
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }

    private fun fetchPrograms() {
        val programRef=fireStore.collection("Programs")
        programRef.get()
            .addOnSuccessListener {documents->
                for (document in documents){
                    val programsNames=document.id
                    programList.add(programsNames)
                }
                val adapter=ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,programList)
                adapter.setDropDownViewResource(androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item)
                binding.ProgramsSpinner.adapter=adapter
            }
    }

    private fun openActivity(selectedProgram: String) {
        binding.LetsGo.setOnClickListener {
            val intent=Intent(this,CoursesActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun verificationOfCode(code: String) {
        val credential=PhoneAuthProvider.getCredential(
           verificationId,
            code
        )
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    binding.Submit.visibility=View.GONE
                    binding.LetsGo.visibility=View.VISIBLE
                    binding.ProgramsSpinner.visibility=View.VISIBLE
                    Toast.makeText(this@RegistrationActivity,"Sign in Successfully",Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this@RegistrationActivity,"failed",Toast.LENGTH_SHORT).show()
                }
            }
    }

    private val callbacks=object:PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
        override fun onVerificationCompleted(p0: PhoneAuthCredential) {
           signWithPhone(p0)
        }

        override fun onVerificationFailed(p0: FirebaseException) {

        }
        override fun onCodeSent(verification: String, p1: ForceResendingToken) {
            verificationId=verification
        }
    }
    private fun signWithPhone(credential: PhoneAuthCredential){
        auth.signInWithCredential(credential)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    binding.Submit.visibility=View.GONE
                    binding.LetsGo.visibility=View.VISIBLE
                    Toast.makeText(this@RegistrationActivity,"Successfully",Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this@RegistrationActivity,"Fail",Toast.LENGTH_SHORT).show()
                }
            }
    }
   /* override fun onStart() {
        super.onStart()
        if(auth.currentUser!=null){
            startActivity(Intent(this,CoursesActivity::class.java))
            finish()
        }
    }*/
    private fun offlineData(selectedPro: String){
        val sharedPreferences=getSharedPreferences("OfflineData",Context.MODE_PRIVATE)
        val editor=sharedPreferences.edit()
        editor.putString("Store", selectedPro)
        editor.apply()
    }

}