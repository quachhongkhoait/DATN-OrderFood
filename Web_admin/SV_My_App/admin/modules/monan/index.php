<?php
	$open = "monan";
    require_once __DIR__."/../../autoload/autoload.php";

    if (isset($_GET['page'])) {
    	$p = $_GET['page'];
    } else {
    	$p = 1;
    }

    $qr = "SELECT * FROM monan,quan_an,loaimonan WHERE monan.ma_qa = quan_an.ma_qa AND monan.maloai = loaimonan.maloai";
    $total = $db->countTable("mamonan","monan");

    $monan = $db->fetchJones("monan",$qr,$total,$p,5,true);
    if (isset($monan['page'])) 
    {
    	$sotrang = $monan['page'];
    	unset($monan['page']);
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
          <li class="breadcrumb-item active"><a href="">Món ăn</a></li>
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
			            <th>Mã món ăn</th>
			            <th>Loại</th>
			            <th>Quán</th>
			            <th>Tên món ăn</th>
			            <th>Giá món ăn</th>
			            <th>Hình ảnh món ăn</th>
			        </tr>
			    </thead>
			    <tbody>
			    	<?php foreach ($monan as $item): ?>
			        <tr>
			            <td><?php echo $item['mamonan']?></td>
			            <td><?php echo $item['tenloai']?></td>
			            <td><?php echo $item['ten_qa']?></td>
			            <td><?php echo $item['tenmonan']?></td>
			            <td><?php echo number_format($item['gia'])?> đ</td>
			            <td><img style="width: 200px; height: 150px" class="img-thumbnail" src="<?php echo $item['hinhanhmonan']?>" alt=""></td>
			            <td>
			            	<div class="btn-group-vertical">
			            		<a class="btn btn-xs btn-info" href="edit.php?mamonan=<?php echo $item['mamonan']?>"><i class="fa fa-edit"></i> Sửa</a>
								<a class="btn btn-xs btn-danger" href="delete.php?mamonan=<?php echo $item['mamonan']?>"><i class="fa fa-times"></i>Xóa</a>
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