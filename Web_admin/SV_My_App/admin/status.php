<?php
    require_once __DIR__."/autoload/autoload.php";

    $ma_dondh = intval(getInput("ma_dondh"));
    $edit_status = $db->fetchID("don_dathang","ma_dondh",$ma_dondh);
    if (empty($edit_status)) {
    	$_SESSION['error'] =  "Dữ liệu không tồn tại!";
    	header("location: /SV_My_App/admin");
    } else {
        $status = $edit_status["status"] ==0 ? 1 : 0;
        $update = $db->update("don_dathang",array("status" => $status),array("ma_dondh"=>$ma_dondh));
            // print_r($insert);
            if ($update > 0) {
                $_SESSION['success'] =  "Duyệt thành công!";
                redirectAdmin("transaction");
            } else {
                // thêm thất bại
                $_SESSION['error'] =  "Duyệt thất bại!";
                redirectAdmin("transaction");
            }
    }


?>