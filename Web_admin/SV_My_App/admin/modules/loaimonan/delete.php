<?php
	$open = "loaimonan";
    require_once __DIR__."/../../autoload/autoload.php";

    $maloai = intval(getInput("maloai"));

    $delete_loai = $db->fetchID("loaimonan","maloai",$maloai);
    if (empty($delete_loai)) {
    	$_SESSION['error'] =  "Dữ liệu không tồn tại!";
    	redirectAdmin("loaimonan");
    }
    $num = $db->delete("loaimonan","maloai",$maloai);
    if ($num>0) {
    	$_SESSION['success'] =  "Xóa thành công!";
    	redirectAdmin("loaimonan");
    } else {
    	$_SESSION['error'] =  "Xóa thất bại!";
    	redirectAdmin("loaimonan");
    }
?>