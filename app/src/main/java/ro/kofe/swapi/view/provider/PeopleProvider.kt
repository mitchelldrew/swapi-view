package ro.kofe.swapi.view.provider

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ro.kofe.swapi.model.People
import ro.kofe.swapi.model.Page
import ro.kofe.swapi.presenter.provider.IProvider
import ro.kofe.swapi.presenter.provider.IProviderListener

class PeopleProvider(private val service:PeopleService): IProvider<People> {
    private var listeners = ArrayList<IProviderListener<People>>()

    override fun get() {
        service.get().enqueue(object: Callback<Page<People>> {
            override fun onResponse(call: Call<Page<People>>, response: Response<Page<People>>) {
                if(response.isSuccessful && response.body() != null){
                    for (listener in listeners)
                        listener.onReceive(response.body()!!.results)
                }
            }

            override fun onFailure(call: Call<Page<People>>, t: Throwable) {
                for (listener in listeners) listener.onError(Exception(t))
                throw RuntimeException(t)
            }
        } )
    }

    override fun removeListener(listener: IProviderListener<People>) {
        listeners.remove(listener)
    }

    override fun addListener(listener: IProviderListener<People>) {
        listeners.add(listener)
    }
}