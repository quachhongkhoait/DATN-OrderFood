<?php
	require 'dbconnect.php';
	/** Array for JSON response*/
	$response = array();
	if($_SERVER['REQUEST_METHOD']=='POST'){
		$taikhoan = $_POST['taikhoan'];
		$matkhau = $_POST['matkhau'];
		$sql = "SELECT * FROM khachhang WHERE taikhoan = '$taikhoan' AND matkhau='$matkhau'";
		if(mysqli_num_rows(@mysqli_query($conn,$sql)) > 0){
			    $result= mysqli_query($conn,$sql);
                $row = mysqli_fetch_array($result);
                $ma_kh = $row["ma_kh"];
				$taikhoan = $row["taikhoan"];
				$matkhau = $row["matkhau"];
				$tenkh = $row["tenkh"];
				$email = $row["email"];
				$sodienthoai = $row["sodienthoai"];
				$diachi = $row["diachi"];
				// $status = $row["status"];
				
				
				$response["success"] = 1;
				$response["message"] = "Đăng nhập thành công";
				$response["ma_kh"] = $ma_kh;
				$response["taikhoan"] = $taikhoan;
				$response["matkhau"] = $matkhau;
				$response["tenkh"] = $tenkh;
				$response["email"] = $email;
				$response["sodienthoai"] = $sodienthoai;
				$response["diachi"] = $diachi;
				// $response["status"] = $status;
		}else{
			$response["success"] = 0;
			$response["message"] = "Đăng nhập thất bại.";
		}
		/**Return json*/
		echo json_encode($response);
	} 
?>

<!-- https://androidcoban.com/viet-ung-dung-login-register-don-gian-su-dung-thu-vien-volley-va-php.html -->
