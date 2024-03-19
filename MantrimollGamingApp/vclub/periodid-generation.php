<?php 
include("include/connection.php");
if($_POST['type']=='generate'){
	$checkperiod_Query=mysqli_query($con,"select * from `tbl_gameid` order by id desc limit 1");
$periodidRow=mysqli_fetch_array($checkperiod_Query);
	echo $periodidRow['gameid'].'~'.'';
$futureid=$_POST['futureid'];
$firstperiodid=date('dmY').sprintf("%03d",1);
$nextid=$futureid+1;
if($futureid==$periodidRow['gameid'])
{

$sql1=mysqli_query($con,"INSERT INTO `tbl_gameid` (`gameid`) VALUES ('".($nextid)."')");
echo $nextid.'~'.($nextid+1);
	

}else
{
	
$sql1=mysqli_query($con,"INSERT INTO `tbl_gameid` (`gameid`) VALUES ('".($futureid+1)."')");
echo $futureid.'~'.($futureid+1);
	
	
	
}}

?>