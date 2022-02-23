package ro.kofe.swapi.view.provider

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ro.kofe.swapi.model.Film
import ro.kofe.swapi.model.Page
import ro.kofe.swapi.presenter.provider.IProvider
import ro.kofe.swapi.presenter.provider.IProviderListener

class FilmProvider(private val service:FilmService): IProvider<Film> {
    private var listeners = ArrayList<IProviderListener<Film>>()

    override fun get() {
        service.get().enqueue(object: Callback<Page<Film>> {
            override fun onResponse(call: Call<Page<Film>>, response: Response<Page<Film>>) {
                if(response.isSuccessful && response.body() != null){
                    for (listener in listeners)
                        listener.onReceive(response.body()!!.results)
                }
            }

            override fun onFailure(call: Call<Page<Film>>, t: Throwable) {
                for (listener in listeners) listener.onError(Exception(t))
            }
        } )
    }

    override fun removeListener(listener: IProviderListener<Film>) {
        listeners.remove(listener)
    }

    override fun addListener(listener: IProviderListener<Film>) {
        listeners.add(listener)
    }
}