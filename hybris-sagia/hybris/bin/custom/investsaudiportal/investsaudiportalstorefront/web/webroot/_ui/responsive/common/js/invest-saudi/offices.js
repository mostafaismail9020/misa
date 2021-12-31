var latitutde = parseFloat(document.getElementById("hfLat").value);
var longtitude = parseFloat(document.getElementById("hfLng").value);
var infoWindowContent = $.trim($("#map-location").html()), map, marker, infowindow, center = { lat: latitutde, lng: longtitude }, zoomLevel = 17, mapIcon = ACC.config.themeResourcePath + '/images/google_marker.png';

function initMap() {
    map = new google.maps.Map(document.getElementById('contactLoc'), {
        panControl: true, center: center, zoom: zoomLevel, mapTypeControl: false, zoomControl: true,streetViewControl:false,
        mapTypeControlOptions: { style: google.maps.MapTypeControlStyle.DROPDOWN_MENU },
        zoomControlOptions: { style: google.maps.ZoomControlStyle.LARGE, position: google.maps.ControlPosition.RIGHT_BOTTOM },
        styles: [{"featureType":"all","elementType":"labels.text.fill","stylers":[{"saturation":36},{"color":"#000000"},{"lightness":40}]},{"featureType":"all","elementType":"labels.text.stroke","stylers":[{"visibility":"on"},{"color":"#000000"},{"lightness":16}]},{"featureType":"all","elementType":"labels.icon","stylers":[{"visibility":"off"}]},{"featureType":"administrative","elementType":"geometry.fill","stylers":[{"color":"#000000"},{"lightness":20}]},{"featureType":"administrative","elementType":"geometry.stroke","stylers":[{"color":"#000000"},{"lightness":17},{"weight":1.2}]},{"featureType":"landscape","elementType":"geometry","stylers":[{"color":"#000000"},{"lightness":20}]},{"featureType":"poi","elementType":"geometry","stylers":[{"color":"#000000"},{"lightness":21}]},{"featureType":"road.highway","elementType":"geometry.fill","stylers":[{"color":"#000000"},{"lightness":17}]},{"featureType":"road.highway","elementType":"geometry.stroke","stylers":[{"color":"#000000"},{"lightness":29},{"weight":0.2}]},{"featureType":"road.arterial","elementType":"geometry","stylers":[{"color":"#000000"},{"lightness":18}]},{"featureType":"road.local","elementType":"geometry","stylers":[{"color":"#000000"},{"lightness":16}]},{"featureType":"transit","elementType":"geometry","stylers":[{"color":"#000000"},{"lightness":19}]},{"featureType":"water","elementType":"geometry","stylers":[{"color":"#000000"},{"lightness":17}]}]
    });
}

$(document).ready(function () {
    $(".js-show-map").each(function (index) {
        var position = new google.maps.LatLng($(this).attr("data-type-lat"), $(this).attr("data-type-lng"));
        var tempMarker = new google.maps.Marker({ draggable: false, position: position, animation: google.maps.Animation.DROP, icon: mapIcon });
        var infowindow = new google.maps.InfoWindow({ content:'<div class="infoWindowbox1 p-1">'+$(this).parent().find(".b-map").html()+'</div>',maxWidth: 200 });
        var moveEnd = google.maps.event.addListener(tempMarker, 'click', function () {
            if (infowindow) { infowindow.close(); }
            infowindow.open(map, tempMarker);
        });
        tempMarker.setMap(map);
    });

    $('.js-show-map').click(function () {
        $("html,body").animate({scrollTop: 100});
        map.panTo(new google.maps.LatLng($(this).attr("data-type-lat"), $(this).attr("data-type-lng")));
    });

    $("a.js-show-map").on("click",function(){
        var _lat = parseFloat($(this).attr("data-type-lat"));
        var _lng = parseFloat($(this).attr("data-type-lng"));
        $("html,body").animate({
            scrollTop: 100
        },function() {
            map.panTo(new google.maps.LatLng(_lat, _lng));
        });
    })
});
