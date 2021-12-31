var marker;
var map;
var geocoder;
var hasMarkerBeenMoved=false;

function setMarker()
{
    if(marker != null)
        marker.setMap(null);

    marker = new google.maps.Marker({
        position: getWassel(),
        map: map,
        draggable:true
    });

    if(map) {
        map.setCenter(getWassel());
        map.setZoom(8);
    }


    google.maps.event.addListener(marker, "dragend", function(event) {
        hasMarkerBeenMoved = true;
        var lat = event.latLng.lat();
        var lng = event.latLng.lng();
        selectedBranch.latitude = lat;
        selectedBranch.longitude = lng;

        geocodeLatLng(geocoder, map, event.latLng);
    });
}

function getWassel()
{
    var wassel;

    if(selectedBranch != undefined && 'latitude' in selectedBranch && 'longitude' in selectedBranch)
        wassel = {lat: selectedBranch.latitude, lng: selectedBranch.longitude }
    else
        wassel = {lat: 24.710561, lng: 46.718758}; //default Ryad marker

    return wassel;
}

function initMap() {
    var wassel = {lat: 24.710561, lng: 46.718758};
    geocoder = new google.maps.Geocoder;
    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 8,
        center: getWassel()
    });
}

function geocodeLatLng(geocoder, map, latlng) {
    var results;
    geocoder.geocode({'location': latlng}, function(results, status) {
        if (status === 'OK' && results[0])
            $('#gMapAddress').val(results[0]['formatted_address']);
    });
}