<?php
	require 'dbconnect.php';
	/** Array for JSON response*/
	$response = array();
	if(true){//$_SERVER['REQUEST_METHOD'] == 'POST'
		$taikhoan = $_POST['taikhoan'];
		$matkhau = $_POST['matkhau'];
		$email = $_POST['email'];
		$sodienthoai = $_POST['sodienthoai'];
		$tenkh = $_POST['tenkh'];
		// $taikhoan = "khoane";
		// $matkhau = "1241";
		// $tenkh = "Quách Hông Khoa";
		// $email = "cj111";
		// $sodienthoai = "0985465057";
		$sqlCheck = "SELECT taikhoan FROM khachhang WHERE taikhoan = '$taikhoan'";
		$result = @mysqli_query($conn,$sqlCheck);
		if (mysqli_num_rows($result) != 0) {
				$response["success"] = 0;
				$response["message"] = "Tên đăng nhập đã có người sử dụng!";
			} else {	
				$sqlInsert = "INSERT INTO khachhang(taikhoan,matkhau,tenkh,email,sodienthoai) VALUES ('$taikhoan','$matkhau','$tenkh','$email','$sodienthoai')";
				$resultInsert = @mysqli_query($conn,$sqlInsert);
				if ($resultInsert) {
						$sqlGetInfo = "SELECT taikhoan, matkhau FROM khachhang WHERE taikhoan = '$taikhoan' AND matkhau = '$matkhau'";
						$result = mysqli_query($conn,$sqlGetInfo);
						$row = mysqli_fetch_array($result);
						
						$response["success"] = 1;
						$response["message"] = "Đăng ký thành công!";
						$response["taikhoan"] = $taikhoan;
						$response["tenkh"] = $tenkh;
						$response["sodienthoai"] = $sodienthoai;
						$response["email"] = $email;
				} else {
					$response["success"] = 0;
					$response["message"] = "Đăng ký thất bại!";
				}
			}	
			/**Return json*/
		echo json_encode($response);
	}
?>

<!-- https://androidcoban.com/viet-ung-dung-login-va-register-don-gian-su-dung-thu-vien-volley-va-php-part-2.html -->