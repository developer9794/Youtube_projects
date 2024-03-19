<?php 
include("include/connection.php");

// $getPeriodQuery = mysqli_query($con, "SELECT * FROM `tbl_gameid` ORDER BY id DESC LIMIT 1");
// $getPeriodRow = mysqli_fetch_assoc($getPeriodQuery);
// $periodId = $getPeriodRow['gameid'];
$type1='parity';

$futureid=$_POST['futureid'];

    
$temp="";
 
    if(!$temp==$futureid){
        
        $temp=$futureid;
        $periodId=$futureid;
$today = date('YmdHis');

// $checkResultQuery = mysqli_query($con, "SELECT * FROM `tbl_result` WHERE `periodid` = '".$periodId."'");
// $checkResultRow = mysqli_num_rows($checkResultQuery);


$randomPrice = 50;
$result = 5;
$randomResult = 8;
$color = 'red';
$randomColor = 'green';
$resultType = 'real';

$createdDate = date('Y-m-d H:i:s');
if ($type1 == 'parity') {
    $result1 = rand(0, 9);
    $x = $result1;
    $randomPrice=rand(1000, 100000);
        $price = rand(1000, 100000); // Default price range for parity type

    if ($x == 1 || $x == 3 || $x == 7 || $x == 9) {
        $price = rand(1, 10000);
        $color = 'green';
    } else if ($x == 2 || $x == 4 || $x == 6 || $x == 8) {
        $color = 'red';
    } else if ($x == 0 || $x == 5) {
        $color = 'red';
    }

    $insertQuery = "INSERT INTO tbl_result (periodid, price, randomprice, result, randomresult, color, randomcolor, resulttype, tabtype, createdate) 
    VALUES ('$periodId', '$price', '$randomPrice', '$result1', '0', '$color', '0', '$resultType', '$type1', '$createdDate')";

    // Execute the query
    if (mysqli_query($con, $insertQuery)) {
        echo "Record inserted successfully.".$periodId."$futureid".$futureid;
    } else {
        echo "Error: " . $insertQuery . "<br>" . mysqli_error($con);
    }
}
}else{
    echo "tbl_result=  result all rady ganrated by system".$futureid;
}


  
?>
