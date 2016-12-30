 <?php  
 require "init.php";  
 
 $sql = "select * from flood_info;";
 
 $result = mysqli_query($con, $sql);
 
 $response = array();
 
 while($row = mysqli_fetch_array($result)){
 
         array_push($response, array("id"=>$row[0],"street"=>$row[1],"latitude"=>$row[2], "longitude"=>$row[3], "height"=>$row[4], "timestamp"=>$row[5])); 
 
 
 }
 
 echo json_encode(array("server_response"=>$response));
 
 
 mysqli_close($con);
 ?>  