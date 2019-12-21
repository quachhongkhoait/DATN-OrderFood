 <?php 
 	require 'dbconnect.php';
 	$tenkhachhang = $_POST['tenkhachhang'];
 	$sodienthoai = $_POST['sodienthoai'];
 	$email = $_POST['email'];
 	if(strlen($tenkhachhang)>0 && strlen($email) && strlen($sodienthoai)){
 		$query = "INSERT INTO donhang VALUES(null, '$tenkhachhang', '$sodienthoai', '$email')";
 		if(mysqli_query($conn, $query)){
 			 $iddonhang = $conn -> insert_id;
 			echo $iddonhang;
 		}
		else 
			echo "error";
 	} else {
 		echo "Bạn hãy kiểm tra lại các dữ liệu";
 	}	
  ?>