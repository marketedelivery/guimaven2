$(document).ready(function(){
 
	if( navigator.geolocation ){
	
		navigator.geolocation.getCurrentPosition(success, fail);
		
}
	else
		 $("p").html("Localização do usuario não encontrada");
});
 
function success(position)
{	var googleLatLng = new google.maps.LatLng(position.coords.latitude, 
		  position.coords.longitude);
	var latitude = position.coords.latitude;
	var longitude =  position.coords.longitude;
	
}
function calcRota(){
	var directionsDisplay = new google.maps.DirectionsRenderer();
	var service = new google.maps.DirectionsService();
	var recuperaJson = $('input[id$="json"]').val();
	
	recuperaJson =  jQuery.parseJSON(recuperaJson);
	
	var destino = new google.maps.LatLng(recuperaJson[0].latitude, recuperaJson[0].longitude);
	var request = {destination: destino,
					origin: origem, 
					travelMode:google.maps.TravelMode.DRIVING };
	var gmap = PF('map').getMap();
	for(var i in gmap.markers){
		gmap.markers[i].setMap(null);
	}
	directionsDisplay.setMap(gmap); 
	directionsDisplay.setPanel(document.getElementById("directionsPanel")); 
	service.route(request,function(response,status){
		if (status == google.maps.DirectionsStatus.OK){
			directionsDisplay.setDirections(response);
		}else{
			window.alert('localização do Usuario não disponivel no momento ' + status);
		}
	});
}	
	
function fail(error)
{
	var errorType = {
0: "Unknown Error",
1: "Permission denied by the user",
2: "Position of the user not available",
3: "Request timed out"
	};
 
	var errMsg = errorType[error.code];
 
	if(error.code == 0 || error.code == 2){
		errMsg = errMsg+" - "+error.message;
	}
 
	$("p").html(errMsg);
}