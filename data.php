 <?php
    include('init.php');
    
     $sql = "select * from flood_info;";
     $count = 0;
     
     $result = mysqli_query($con, $sql);
     while($row = mysqli_fetch_array($result,MYSQLI_ASSOC)){
        $ids[$count] = $row['ID'];
        $latitudes[$count] =$row['latitude'];
        $longitudes[$count] =$row['longitude'];
        $devices[$count] =$row['deviceid'];
        $heights[$count] =$row['height'];
        $timestamps[$count] =$row['timestamp'];
        
        $count = $count+1;
        }
     
   echo "<table>";
     echo "<tr><th>ID</th><th>DEVICEID</th><th>LATITUDE</th><th>LONGITUDE</th><th>HEIGHT</th><th>TIMESTAMP</th></tr>";
     
          for( $i = 0; $i<$count;$i++){
            
          
          echo "<tr><td>";
          echo "$ids[$i]"; 
          echo "</td><td>";
          echo "$devices[$i]";
          echo "</td><td>";
          echo "$latitudes[$i]";
          echo "</td><td>";
          echo "$longitudes[$i]";
          echo "</td><td>";
          echo "$heights[$i]";
          echo "</td><td>";
          echo "$timestamps[$i]";
          echo "</td></tr>";
          }
    
    $i =0;
   echo "</table>";
    
    ?>