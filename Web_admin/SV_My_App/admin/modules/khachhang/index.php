<?php
	$open = "khachhang";
    require_once __DIR__."/../../autoload/autoload.php";

    if (isset($_GET['page'])) {
    	$p = $_GET['page'];
    } else {
    	$p = 1;
    }

    $qr = "SELECT * FROM khachhang";
    $total = $db->countTable("ma_kh","khachhang");

    $kh = $db->fetchJones("khachhang",$qr,$total,$p,10,true);
    if (isset($kh['page'])) 
    {
    	$sotrang = $kh['page'];
    	unset($kh['page']);
    }

    // $qr = "SELECT * FROM monan,quan_an,loaimonan WHERE monan.ma_qa = quan_an.ma_qa AND monan.maloai = loaimonan.maloai";
    // $monan = $db->fetchsql($qr);
    //

?>
<?php require_once __DIR__."/../../layouts/hearder.php"; ?>

    <div id="content-wrapper">

      <div class="container-fluid">

        <!-- Breadcrumbs-->
        <ol class="breadcrumb">
          <li class="breadcrumb-item">
            Home
          </li>
          <li class="breadcrumb-item active"><a href="">Khách hàng</a></li>
        </ol>
      </div>
      <!-- /.container-fluid -->
      <div class="card mb-3">
          <div class="card-header"><a class="btn btn-success" href="add.php">Thêm mới</a></div>
<?php require_once __DIR__."/../../partials/notification.php"; ?>
          <div class="card-body">
			<table class="table table-bordered">
			    <thead>
			        <tr>
			            <th>#</th>
			            <th>Tài khoản</th>
			            <th>Trạng thái</th>
			            <th>Tên khách hàng</th>
			            <th>Email</th>
			            <th>Số điện thoại</th>
			            <th>Địa chỉ</th>
			        </tr>
			    </thead>
			    <tbody>
			    	<?php foreach ($kh as $item): ?>
			        <tr>
			            <td><?php echo $item['ma_kh']?></td>
			            <td><?php echo $item['taikhoan']?></td>
			            <td>
			            	<?php if($item['status']== 0): ?>
				                <a href="status.php?ma_kh=<?php echo $item['ma_kh']?>" class="btn btn-xs text-white <?php echo "btn-danger" ?>">Block</a>
				            <?php else: ?>
				                <a href="status.php?ma_kh=<?php echo $item['ma_kh']?>" class="btn btn-xs text-white <?php echo "btn-info" ?>">Good</a>
				            <?php endif ?>
			            </td>
			            <td><?php echo $item['tenkh']?></td>
			            <td><?php echo $item['email']?></td>
			            <td><?php echo $item['sodienthoai']?></td>
			            <td><?php echo $item['diachi']?></td>
			            <td>
			            	<div class="btn-group-vertical">
			            		<a class="btn btn-xs btn-info" href="edit.php?ma_kh=<?php echo $item['ma_kh']?>"><i class="fa fa-edit"></i> Sửa</a>
								<a class="btn btn-xs btn-danger" href="delete.php?ma_kh=<?php echo $item['ma_kh']?>"><i class="fa fa-times"></i>Xóa</a>
			            	</div>
			            </td>
			        </tr>
			    	<?php endforeach ?>
			    </tbody>
			</table>
			<div class="pull-right">
				<nav style="float: right;">
			    <ul class="pagination">
			    	<?php if ($p > 1 && $total > 1) { ?>
			        <li class="page-item"><a class="page-link" href="?page=<?php echo ($p-1); ?>">Previous</a></li>
			    <?php } ?>
			        <?php for ($i = 1; $i <= $sotrang; $i++): ?>
			        	<?php 
			        	if (isset($_GET['page'])) {
			        		$p = $_GET['page'];
			        	} else {
			        		$p=1;
			        	} ?>
			        <li class="page-item <?php echo ($i == $p) ? 'active' : ' ' ?>">
			        	<a class="page-link" href="?page=<?php echo $i; ?>"> <?php echo $i; ?></a>
			        </li>
			        <?php endfor ?>
			        <?php if ($p < $total && $total > 1) { ?>
			        <li class="page-item"><a class="page-link" href="?page=<?php echo ($p+1); ?>">Next</a></li>
			        <?php } ?>
			    </ul>
			</nav>
			</div>
          </div>
        </div>

<?php require_once __DIR__. "/../../layouts/footer.php"; ?>