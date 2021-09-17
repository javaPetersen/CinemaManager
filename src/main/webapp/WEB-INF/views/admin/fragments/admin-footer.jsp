<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: peter
  Date: 07.09.2021
  Time: 11:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Footer -->
<footer class=" bg-white">
  <div class="container my-auto">
    <div class="copyright text-center my-auto">
      <span>Copyright &copy; CinemaManager 2021</span>
    </div>
  </div>
</footer>
<!-- End of Footer -->

</div>
<!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
  <i class="fas fa-angle-up"></i>
</a>

<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">×</span>
        </button>
      </div>
      <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
      <div class="modal-footer">
        <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
        <a class="btn btn-primary" href="login.html">Logout</a>
      </div>
    </div>
  </div>
</div>

<!-- Bootstrap core JavaScript-->

<script src="<c:url value="${pageContext.request.contextPath}/resources/theme/vendor/jquery/jquery.min.js" />"></script>
<script src="<c:url value="${pageContext.request.contextPath}/resources/theme/vendor/bootstrap/js/bootstrap.bundle.min.js" />"></script>

<!-- Core plugin JavaScript-->
<script src="<c:url value="${pageContext.request.contextPath}/resources/theme/vendor/jquery-easing/jquery.easing.min.js" />"></script>

<!-- Custom scripts for all pages-->
<script src="<c:url value="${pageContext.request.contextPath}/resources/theme/js/sb-admin-2.min.js" />"></script>

<!-- Page level plugins -->
<script src="<c:url value="${pageContext.request.contextPath}/resources/theme/vendor/chart.js/Chart.min.js" />"></script>

<!-- Page level custom scripts -->
<script src="<c:url value="${pageContext.request.contextPath}/resources/theme/js/demo/chart-area-demo.js" />"></script>
<script src="<c:url value="${pageContext.request.contextPath}/resources/theme/js/demo/chart-pie-demo.js" />"></script>

</body>
</html>