        $(document).ready(function () {
            $("#formSearch").submit(function (e) {
                e.preventDefault();
                paginationSearch(this, 1);
                return;
            });
        });

        function paginationSearch(formSearch, page) {
            var action = $(formSearch).prop("action");
            var method = $(formSearch).prop("method");
            var container = $(formSearch).data("container");

            var searchData = $(formSearch).serializeArray();
            searchData.push({ "name": "Page", "value": page });
			console.log(action);
            $.ajax({
                url: action,
                type: method,
                data: searchData,
                async: false,
                error: function () {
                    alert("Your request is not valid!");
                },
                success: function (data) {
                    $(container).html(data);
                }
            });
        }