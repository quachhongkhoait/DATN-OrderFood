<?php
    require_once __DIR__."/../../autoload/autoload.php";

    $ma_kh = intval(getInput("ma_kh"));
    $edit_status = $db->fetchID("khachhang","ma_kh",$ma_kh);
    if (empty($edit_status)) {
    	$_SESSION['error'] =  "Dữ liệu không tồn tại!";
    	header("location: /SV_My_App/admin");
    } else {
        $status = $edit_status["status"] ==0 ? 1 : 0;
        $update = $db->update("khachhang",array("status" => $status),array("ma_kh"=>$ma_kh));
            // print_r($insert);
            if ($update > 0) {
                $_SESSION['success'] =  "Đổi thành công!";
                redirectAdmin("khachhang");
            } else {
                // thêm thất bại
                $_SESSION['error'] =  "Đổi thất bại!";
                redirectAdmin("khachhang");
            }
    }


?>