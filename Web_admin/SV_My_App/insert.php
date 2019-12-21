<?php 
	require 'dbconnect.php';
	// $file_path = "image/";
	// $file_path = $file_path.basename($_FILES['uploaded_file']['name']);

	// if (move_uploaded_file($_FILES['uploaded_file']['tmp_name'], $file_path)) {
	// 	echo $_FILES['uploaded_file']['name'];
	// } else{
	// 	echo "failed  ".$_FILES['uploaded_file']['error'];
	// }
	$taikhoan = $_POST['taikhoan'];
	$matkhau = $_POST['matkhau'];
	$hinhanh = $_POST['hinhanh'];

	if (strlen($taikhoan)>0 && strlen($matkhau)>0 && strlen($hinhanh)>0) {
		$query = 'INSERT INTO admin VALUES(null,"$taikhoan","$matkhau","$hinhanh")';
		$data = mysql_query($conn,$query);
		if ($data) {
			echo "thanhcong";
			# code...
		} else{
			echo "loi";
		}
	} else{
		echo "nulll";
	}
 ?>