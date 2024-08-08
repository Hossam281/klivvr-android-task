import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.klivvrandroidtask.R
import com.example.klivvrandroidtask.data.model.City
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStreamReader

class CityViewModel(private val application: Application) : AndroidViewModel(application) {
    private val _cities = MutableLiveData<List<City>>()
    val cities: LiveData<List<City>> get() = _cities

    private var allCities = mutableListOf<City>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadCities()
        }
    }

    private fun loadCities() {
        try {
            val inputStream = application.resources.openRawResource(R.raw.cities)
            val reader = InputStreamReader(inputStream, "UTF-8")
            val parsedCities = Gson().fromJson(reader, Array<City>::class.java).toList()
            reader.close()

            // Sort the list of cities alphabetically by their names
            allCities.addAll(parsedCities)
            allCities.sortBy { "${it.name}, ${it.country}" }

            _cities.postValue(allCities)
        } catch (e: JsonSyntaxException) {
            Log.e("CityViewModel", "Failed to parse cities JSON", e)
        } catch (e: Exception) {
            Log.e("CityViewModel", "An unexpected error occurred while loading cities", e)
        }
    }

    fun filterCities(prefix: String) {
        viewModelScope.launch {
            val filteredCities = withContext(Dispatchers.IO) {
                val lowerBound = findLowerBound(allCities, prefix)
                val upperBound = findUpperBound(allCities, prefix)
                if (lowerBound >= 0 && upperBound >= 0) {
                    allCities.subList(lowerBound, upperBound)
                } else {
                    emptyList()
                }
            }
            _cities.postValue(filteredCities)
        }
    }

    private fun findLowerBound(list: List<City>, prefix: String): Int {
        var low = 0
        var high = list.size

        while (low < high) {
            val mid = (low + high) / 2
            val formattedCityWithCountryName = "${list[mid].name}, ${list[mid].country}"
            if (formattedCityWithCountryName > prefix) {
                high = mid
            } else if (formattedCityWithCountryName == prefix) {
                low = mid
                break
            } else {
                low = mid + 1
            }
        }
        return low
    }

    private fun findUpperBound(list: List<City>, prefix: String): Int {
        var low = 0
        var high = list.size

        while (low < high) {
            val mid = (low + high) / 2
            val formattedCityWithCountryName = "${list[mid].name}, ${list[mid].country}"
            if (formattedCityWithCountryName > prefix + Char.MAX_VALUE) {
                high = mid
            } else {
                low = mid + 1
            }
        }
        return low
    }
}
