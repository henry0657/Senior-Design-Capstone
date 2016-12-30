<!DOCTYPE html>
<html>
  <head>
    
  <link rel="stylesheet" href="http://www.roadsideflooddetector.co.nf/style.css">
  </head>
  <body>
  <h2>Display data from DB</h2>
   <?php include('data.php') ?>
    
    <h1>Road Side Flood Detector Map</h1>
    
    
     <div id="map"></div>
     <script>
      function initMap() {
        var i = 0;
        var end = <?=$count?>;
        var middle = {lat:0.0,lng:0.0};
        var contentString = 
                '<h1 id ="firstHeading" class="firstHeading">Device ID: '+ <?=end($devices)?> + '</h1>'
                +'<p><b>Height is  ' + '<h3>' + <?=end($heights) ?> +  ' inches </h3>' + ' at: ' + '<?=end($timestamps)?>' + '</b></p></div>';
        var markers = [];
        
        var mark = ({lat: <?=end($latitudes)?>,lng:<?=end($longitudes)?>});
        var map = new google.maps.Map(document.getElementById('map'), {
          zoom: 6,
          center: mark
        });
        
       
        var marker =new google.maps.Marker({
          position:mark, 
          animation: google.maps.Animation.DROP
          
        });
        
        var infowindow = new google.maps.InfoWindow({
            content: contentString
            });
        marker.setMap(map)
        marker.addListener('click', function(){
                infowindow.open(map, marker);
                });
        }
        
    </script>
    
    
    
    <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAXg6CBqg1fuMJnKcQJSw8UnEOeNwIJP-k&callback=initMap">
    </script>
    
    
    
    
    
   
  </body>
</html>