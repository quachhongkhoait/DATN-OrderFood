<?php
	$open = "khachhang";
    require_once __DIR__."/../../autoload/autoload.php";

    $ma_kh = intval(getInput("ma_kh"));

    $delete_kh = $db->fetchID("khachhang","ma_kh",$ma_kh);
    if (empty($delete_kh)) {
    	$_SESSION['error'] =  "Dữ liệu không tồn tại!";
    	redirectAdmin("khachhang");
    }
    $num = $db->delete("khachhang","ma_kh",$ma_kh);
    if ($num>0) {
    	$_SESSION['success'] =  "Xóa thành công!";
    	redirectAdmin("khachhang");
    } else {
    	$_SESSION['error'] =  "Xóa thất bại!";
    	redirectAdmin("khachhang");
    }
?>