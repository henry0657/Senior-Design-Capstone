<?php
require "init.php";
date_default_timezone_set('America/Chicago');

$data = $_POST['data'];
$json = json_decode($data,true);


$name = $_POST['coreid'];
$devicename = substr($name,-5);
$height = $json['height'];
$latitude = $json['latitude'];
$longitude = $json['longitude'];
$timestamp = date('Y-m-d G:i:s');
$id= "id";

$sql_query = "insert into flood_info values ('$id','$devicename', '$latitude', '$longitude', '$height', '$timestamp');";


if(mysqli_query($con,$sql_query))
{
 echo "\nDatabase addition added!";
}
else
{
echo " Data insertion error..".mysqli_error($con);
}

mysqli_close($con);
?>
