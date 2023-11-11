package com.example.senakitchnew

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.DatePicker
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.util.PatternsCompat
import com.bumptech.glide.Glide
import com.example.senakitchnew.Connection.ApiConnection
import com.example.senakitchnew.ImportClasses.KeyBoard
import com.example.senakitchnew.ImportClasses.popupalert
import com.example.senakitchnew.databinding.ActivityEditperfilBinding
import com.example.senakitchnew.send.RegisterSend
import com.example.senakitchnew.send.User
import com.example.senakitchnew.send.UserAdmin
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
@Suppress("NAME_SHADOWING")
class  activity_editperfil : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityEditperfilBinding

    private val toast = popupalert()
    private var keyBoard = KeyBoard()

    private lateinit var viewRoot: LinearLayout
    private lateinit var name: EditText
    private lateinit var email: EditText
    private lateinit var checkMasculino: CheckBox
    private lateinit var checkFemenino: CheckBox
    private lateinit var checkOtro: CheckBox
    private lateinit var fechaNacimientoEditText: EditText
    private lateinit var btnSeleccionarFecha: CircleImageView
    private lateinit var tvname: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvCheckBox: TextView
    private lateinit var tvFechaNacimiento: TextView

    private lateinit var cerrarSesion: Button

    private lateinit var profileImage: CircleImageView

    @SuppressLint("SetTextI18n", "InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditperfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))

        initData() //Esta vista no la estas usando, estas usando el Fragment

    }//Fin onCreate


    private fun initData() {

        val userId = UserAdmin.getUserId()

        Log.e("IDUSER", "${userId}")

        getUserProfile(userId.toString())

        profileImage = findViewById(R.id.profile_image)

        keyBoard

        buttonSheet()

    }

//    @SuppressLint("MissingInflatedId", "UseCompatLoadingForDrawables", "SuspiciousIndentation")
//    private fun Dialog() {
//
//        //Vista
//        val view                = layoutInflater.inflate(R.layout.edit_profile, null)
//
//        viewRoot                = view.findViewById(R.id.viewRoot)
//        hideKeyboard()
//
//        name                    = view.findViewById(R.id.name)
//        email                   = view.findViewById(R.id.email)
//        btnSeleccionarFecha     = view.findViewById(R.id.btnSeleccionarFecha)
//        fechaNacimientoEditText = view.findViewById(R.id.fechaNacimientoEditText)
//        checkMasculino          = view.findViewById(R.id.check_box_masculino)
//        checkFemenino           = view.findViewById(R.id.check_box_femenino)
//        checkOtro               = view.findViewById(R.id.check_box_otro)
//
//        tvname                  = view.findViewById(R.id.tvName)
//        tvEmail                 = view.findViewById(R.id.tvEmail)
//        tvCheckBox              = view.findViewById(R.id.tvCheckBox)
//        tvFechaNacimiento       = view.findViewById(R.id.tvFechaNacimiento)
//
//        btnSeleccionarFecha.setOnClickListener(this)
//
//        val alertDialog = MaterialAlertDialogBuilder(this)
//
//        alertDialog.setTitle(resources.getString(R.string.title))
//        alertDialog.setIcon(R.drawable.logo)
//        alertDialog.setNegativeButtonIcon(resources.getDrawable(R.drawable.cerrar, theme))
//        alertDialog.setPositiveButtonIcon(resources.getDrawable(R.drawable.enviar, theme))
//
//        alertDialog.setView(view)
//        val dialog: AlertDialog = alertDialog.create()
//        dialog.show()//.setPositiveButtonIcon(resources.getDrawable(R.drawable.logo, theme))
//        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
//            if (validateEmail() and validateNameAndDate() and validateCheckBox()) {
//                if (name.text.toString().isEmpty() && !email.text.toString()
//                        .isEmpty() && !fechaNacimientoEditText.text.toString()
//                        .isEmpty() && validateCheckBox()
//                ) {
//                    toast.toastSuccess(this, "Perfil", "Perfil editado correctamente")
//                    dialog.dismiss()
//                } else {
//                    validate()
//                    toast.toastError(this, "Perfil", "Por favor llena todos los campos!!!")
//                }
//            }
//        }
//        checkBoxValidate(checkMasculino, checkFemenino, checkOtro)
//
//    }


//    private fun checkBoxValidate(
//        checkM: CheckBox,
//        checkF: CheckBox,
//        checkO: CheckBox
//    ): String {//(checkM: CheckBox, checkF:CheckBox, checkO:CheckBox)
//        val checkM = checkM
//        val checkF = checkF
//        val checkO = checkO
//        checkM.setOnCheckedChangeListener { _: CompoundButton, _: Boolean ->
//
//            if (checkM.isChecked) {
//                checkF.isEnabled = false
//                checkO.isEnabled = false
//
//            } else if (!checkM.isChecked) {
//                checkF.isEnabled = true
//                checkO.isEnabled = true
//            }
//
//        }
//
//        checkF.setOnCheckedChangeListener { _: CompoundButton, _: Boolean ->
//
//            if (checkF.isChecked) {
//                checkM.isEnabled = false
//                checkO.isEnabled = false
//
//            } else if (!checkF.isChecked) {
//                checkM.isEnabled = true
//                checkO.isEnabled = true
//            }
//        }
//
//        checkO.setOnCheckedChangeListener { _: CompoundButton, _: Boolean ->
//
//            if (checkO.isChecked) {
//                checkM.isEnabled = false
//                checkF.isEnabled = false
//
//            } else if (!checkO.isChecked) {
//                checkM.isEnabled = true
//                checkF.isEnabled = true
//            }
//        }
//
//        return if (checkM.isChecked) {
//            "Masculino"
//        } else if (checkF.isChecked) {
//            "Femenino"
//        } else {
//            "Otro"
//        }
//
//    }

    private fun validate() {
        val result = arrayOf(validateEmail(), validateNameAndDate(), validateCheckBox())
        if (false in result) {
            return
        }
    }

    @SuppressLint("SetTextI18n")
    private fun validateEmail(): Boolean {
        val email = email.text.toString()
        return if (email.isEmpty()) {
            tvEmail.text = "El campo del correo no puede estar vacio"
            false
        } else if (!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {
            tvEmail.text = "Por favor ingresa un correo valido"
            false
        } else {
            tvEmail.text = null
            true
        }
    }

    @SuppressLint("SetTextI18n")
    private fun validateCheckBox(): Boolean {
        val checkMasculino = checkMasculino.isChecked
        val checkFemenino = checkFemenino.isChecked
        val checkOtro = checkOtro.isChecked

        val tvCheckBox = tvCheckBox

        return if ((!checkMasculino) and (!checkFemenino) and (!checkOtro)) {
            tvCheckBox.text = "Marca alguna casilla"
            false
        } else {
            tvCheckBox.text = null
            true
        }

    }

    @SuppressLint("SetTextI18n")
    private fun validateNameAndDate(): Boolean {
        val name = name.text.toString()
        val fechaNacimiento = fechaNacimientoEditText.text.toString()
        return if (name.isEmpty()) {
            tvname.text = "El campo no puede estar vacio"
            false
        } else if (fechaNacimiento.isEmpty()) {
            tvFechaNacimiento.text = "El campo no puede estar vacio"
            false
        } else {
            tvname.text = null
            tvFechaNacimiento.text = null
            true
        }
    }

    //Mostrar calendario
//    override fun onClick(p0: View?) {
//        val dialogfecha =
//            DatePicker.DatePickerFragment { year, month, day -> mostrarResultado(year, month, day) }
//        dialogfecha.show(supportFragmentManager, "DatePicker")
//    }
//
//    @SuppressLint("SetTextI18n")
//    private fun mostrarResultado(year: Int, month: Int, day: Int) {
//        fechaNacimientoEditText.setText("$year-$month-$day")
//    }
//
//
//    fun hideKeyboard(){
//        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        inputManager.hideSoftInputFromWindow(viewRoot.windowToken, 0)
//    }

    fun getUserLogout() {
        val userLogout: Call<RegisterSend>? = ApiConnection.getApiService().logoutUser()
        userLogout?.enqueue(object : Callback<RegisterSend?> {
            override fun onResponse(
                call: Call<RegisterSend?>,
                response: Response<RegisterSend?>
            ) {
                if (response.isSuccessful) {
                    toast.toastSuccess(this@activity_editperfil, "Cerrar sesión", "Cuenta cerrada con exito!!!")
                } else {
                    toast.toastError(this@activity_editperfil, "Error", "Vuelve a intentarlo!!!")
                }
            }

            override fun onFailure(call: Call<RegisterSend?>, t: Throwable) {
                toast.toastError(this@activity_editperfil, "Error", "Ha sucedido un error")
            }
        })
    }

    @SuppressLint("MissingInflatedId", "UseCompatLoadingForDrawables", "SuspiciousIndentation", "InflateParams")
    private fun buttonSheet() {

//        val view  = layoutInflater.inflate(R.layout.activity_editperfil, null)
//        val modalBottomSheet = ModalBottomSheet()
//        cerrarSesion    = view.findViewById(R.id.btn_cerrar_sesion)

//        val modalBottomSheetBehavior = (modalBottomSheet.dialog as? BottomSheetDialog)?.behavior

//
//        binding.logout.setOnClickListener{
//
//            modalBottomSheetBehavior?.expandedOffset
//            modalBottomSheet.dialog
//            modalBottomSheetBehavior?.halfExpandedRatio
//            modalBottomSheet.show(supportFragmentManager, ModalBottomSheet.TAG)
//
//        }
//
//        cerrarSesion.setOnClickListener{
//            modalBottomSheet.dismiss()
//        }


    }

    /**
     *  Get data of User by id login
     */
    fun getUserProfile(userId: String) {
        val apiService = ApiConnection.getApiService()

        val userProfileCall: Call<User> = apiService.getUserProfile(userId)
        userProfileCall.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    val user = response.body()
                    user?.let {
                        findViewById<TextView>(R.id.name).text          = it.name
                        findViewById<TextView>(R.id.email).text          = it.email
//                        findViewById<TextView>(R.id.genero).text          = it.genero
//                        findViewById<TextView>(R.id.fechaNacimiento).text = it.fechaNacimiento
//                        findViewById<TextView>(R.id.description).text     = it.description

                        Glide.with(this@activity_editperfil)
                            .load(it.profile_photo_url)
                            .placeholder(R.drawable.logo) // Imagen de carga mientras se carga la imagen
                            .error(R.drawable.logo) // Imagen de error si no se puede cargar la imagen
                            .into(profileImage)

                    }
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                toast.toastError(this@activity_editperfil, "Conexión", "Error de conexión")
            }
        })
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

}