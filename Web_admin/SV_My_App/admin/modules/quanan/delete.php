<?php
	$open = "quanan";
    require_once __DIR__."/../../autoload/autoload.php";

    $ma_qa = intval(getInput("ma_qa"));

    $delete_quanan = $db->fetchID("quan_an","ma_qa",$ma_qa);
    if (empty($delete_quanan)) {
    	$_SESSION['error'] =  "Dữ liệu không tồn tại!";
    	redirectAdmin("quanan");
    }
    $num = $db->delete("quan_an","ma_qa",$ma_qa);
    if ($num>0) {
    	$_SESSION['success'] =  "Xóa thành công!";
    	redirectAdmin("quanan");
    } else {
    	$_SESSION['error'] =  "Xóa thất bại!";
    	redirectAdmin("quanan");
    }
?>