<?php
	$open = "quanan";
    require_once __DIR__."/../../autoload/autoload.php";
    if (isset($_GET['page'])) {
    	$p = $_GET['page'];
    } else {
    	$p = 1;
    }

    $qr = "SELECT * FROM quan_an";
    $total = $db->countTable("ma_qa","quan_an");

    $quan_an = $db->fetchJones("quan_an",$qr,$total,$p,2,true);
    if (isset($quan_an['page'])) 
    {
    	$sotrang = $quan_an['page'];
    	unset($quan_an['page']);
    }

?>
<?php require_once __DIR__."/../../layouts/hearder.php"; ?>

    <div id="content-wrapper">

      <div class="container-fluid">

        <!-- Breadcrumbs-->
        <ol class="breadcrumb">
          <li class="breadcrumb-item">
            Home
          </li>
          <li class="breadcrumb-item active"><a href="">Danh sách quán</a></li>
        </ol>
      </div>
      <!-- /.container-fluid -->
      <div class="card mb-3">
          <div class="card-header"><a class="btn btn-success" href="add.php">Thêm quán mới</a></div>
<?php require_once __DIR__."/../../partials/notification.php"; ?>
          <div class="card-body">
			<table class="table table-bordered">
			    <thead>
			        <tr>
			            <th>Mã quán ăn</th>
			            <th>Tên quán ăn</th>
			            <th>Địa chỉ quán</th>
			            <th>Thời gian phục vụ</th>
			            <th>Hình ảnh quán</th>
			        </tr>
			    </thead>
			    <tbody>
			    	<?php foreach ($quan_an as $item): ?>
			        <tr>
			            <td><?php echo $item['ma_qa']?></td>
			            <td><?php echo $item['ten_qa']?></td>
			            <td><?php echo $item['diachi_qa']?></td>
			            <td><?php echo $item['thoigianphucvu']?></td>
			            <td><img style="width: 200px; height: 150px" class="img-thumbnail" src="<?php echo $item['hinhanh_qa']?>" alt=""></td>
			            <td>
			            	<div class="btn-group-vertical">
			            		<a class="btn btn-xs btn-info" href="edit.php?ma_qa=<?php echo $item['ma_qa']?>"><i class="fa fa-edit"></i> Sửa</a>
								<a class="btn btn-xs btn-danger" href="delete.php?ma_qa=<?php echo $item['ma_qa']?>"><i class="fa fa-times"></i>Xóa</a>
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