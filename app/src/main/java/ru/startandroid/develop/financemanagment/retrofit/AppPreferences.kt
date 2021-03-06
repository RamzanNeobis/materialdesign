import android.content.Context
import android.content.SharedPreferences


object AppPreferences {

    const val baseUrl = ""
    private const val NAME = ""
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences


    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var accessToken: String?
        get() = preferences.getString("accessToken", "")
        set(value) = preferences.edit {
            it.putString("accessToken", value)
        }

    var userLogin: String?
        get() = preferences.getString("userLogin", "")
        set(value) = preferences.edit {
            it.putString("userLogin", value)
        }

    var isLogined: Boolean
        get() = preferences.getBoolean("isLogined", false)
        set(value) = preferences.edit {
            it.putBoolean("isLogined", value)
        }

    var name: String
        get() {
            val data = preferences.getString("name", "")
            return if (data != null) return data else ""
        }
        set(value) = preferences.edit {
            it.putString("name", value)
        }

    fun clear (){
        isLogined = false
        accessToken = ""
    }
}
