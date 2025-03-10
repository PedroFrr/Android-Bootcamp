package com.raywenderlich.example.moviesapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.raywenderlich.example.moviesapp.R
import com.raywenderlich.example.moviesapp.utils.hideKeyboard
import com.raywenderlich.example.moviesapp.utils.prefs.SharedPrefManager
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val prefs = SharedPrefManager()
        loginButton.setOnClickListener {
            if(isLoginValid()){
                saveLoginStatus()
                redirectUserToMain(view)
            }
        }
    }

    private fun isLoginValid(): Boolean  {
        val username = usernameEditText.text.toString()
        val password = passwordEditText.text.toString()

        return username.length >= 4 && password.length >= 4
    }

    private fun saveLoginStatus(){
        val prefs = SharedPrefManager()
        prefs.setUserLoggedIn(true)
    }

    private fun redirectUserToMain(view: View){
        this.hideKeyboard()
        Navigation.findNavController(view).navigate(R.id.loginToMain)
    }

    companion object {

        fun newInstance() = LoginFragment()

    }
}