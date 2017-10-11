package br.com.jordilucas.carros.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.com.jordilucas.carros.R
import br.com.jordilucas.carros.domain.Carro
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [MapaFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [MapaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MapaFragment : BaseFragment(), OnMapReadyCallback {


    // TODO: Rename and change types of parameters
    private var map: GoogleMap? = null
    val carro: Carro by lazy { arguments.getParcelable<Carro>("carro") }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_mapa, container, false)
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
        return view

    }

    override fun onMapReady(map: GoogleMap?) {
        this.map = map
        val location = LatLng(carro.latitude.toDouble(), carro.longitude.toDouble())
        val update = CameraUpdateFactory.newLatLngZoom(location, 3f)
        map?.moveCamera(update)
        map?.addMarker(MarkerOptions()
                .title(carro.nome)
                .snippet(carro.desc)
                .position(location))
        map?.mapType = GoogleMap.MAP_TYPE_NORMAL
    }

}
