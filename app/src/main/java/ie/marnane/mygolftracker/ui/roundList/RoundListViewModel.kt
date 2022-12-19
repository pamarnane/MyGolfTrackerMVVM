package ie.marnane.mygolftracker.ui.roundListTBD

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import ie.marnane.mygolftracker.firebase.FirebaseDBManager
import ie.marnane.mygolftracker.models.GolfRoundModel
import timber.log.Timber

class RoundListViewModel : ViewModel() {

    private val golfRounds =
        MutableLiveData<List<GolfRoundModel>>()

    val observableRoundsList: LiveData<List<GolfRoundModel>>
        get() = golfRounds

    var liveFirebaseUser = MutableLiveData<FirebaseUser>()

    init { load() }

    fun load() {
        try {
            FirebaseDBManager.findAll(liveFirebaseUser.value?.uid!!, golfRounds)
            Timber.i("Report Load Success : ${golfRounds.value.toString()}")
        }
        catch (e: Exception) {
            Timber.i("Report Load Error : $e.message")
        }

    }

    fun delete(userid: String, id: String) {
        try {
            //DonationManager.delete(userid,id)
            FirebaseDBManager.delete(userid,id)
            Timber.i("Report Delete Success")
        }
        catch (e: Exception) {
            Timber.i("Report Delete Error : $e.message")
        }
    }
}