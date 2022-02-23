package ro.kofe.swapi.view.provider

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ro.kofe.swapi.model.Planet
import ro.kofe.swapi.model.Page
import ro.kofe.swapi.presenter.provider.IProvider
import ro.kofe.swapi.presenter.provider.IProviderListener

class PlanetProvider(private val service:PlanetService): IProvider<Planet> {
    private var listeners = ArrayList<IProviderListener<Planet>>()

    override fun get() {
        service.get().enqueue(object: Callback<Page<Planet>> {
            override fun onResponse(call: Call<Page<Planet>>, response: Response<Page<Planet>>) {
                if(response.isSuccessful && response.body() != null){
                    for (listener in listeners)
                        listener.onReceive(response.body()!!.results)
                }
            }

            override fun onFailure(call: Call<Page<Planet>>, t: Throwable) {
                for (listener in listeners) listener.onError(Exception(t))
            }
        } )
    }

    override fun removeListener(listener: IProviderListener<Planet>) {
        listeners.remove(listener)
    }

    override fun addListener(listener: IProviderListener<Planet>) {
        listeners.add(listener)
    }
}