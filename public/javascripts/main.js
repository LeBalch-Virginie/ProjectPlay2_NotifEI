jQuery(function($) {
    $("#medicament-search").keyup(function() {
        if ($(this).val() != "") {
            $.getJSON(
                "autocomplete/medicament/" + $(this).val(),
                function(data) {
                    $("#medicament-search").autocomplete({
                        source: data
                    });
                }
            );
        }
    });

    $("#medicament-form").submit(function(e) {
        e.preventDefault();
        $("#medicament-effets").html('<li><img src="/assets/images/wait.gif" height="42" width="42" alt="wait" /></li>');
        $.post(
            "/recherche/medicament",
            { "medicament-search" : $("#medicament-search").val() },
            function(data) {
               $("#medicament-effets").empty();
               if (data.length === 0) {
                    $("#medicament-effets").append("<li>Aucun résultat</li>");
               } else {
                   for (var i = 0; i < data.length; i++) {
                       $("#medicament-effets").append("<li>" + data[i] + "</li>");
                   }
               }
            },
            "json"
        );
        return false;
    });

    $("#dispositif-search").keyup(function() {
        if ($(this).val() != "") {
            $.getJSON(
                "autocomplete/dispositif/" + $(this).val(),
                function(data) {
                    $("#dispositif-search").autocomplete({
                        source: data
                    });
                }
            );
        }
    });

    $("#dispositif-form").submit(function(e) {
        e.preventDefault();
        $("#dispositif-effets").html('<li><img src="/assets/images/wait.gif" height="42" width="42" alt="wait" /></li>');
        $.post(
            "/recherche/dispositif",
            { "dispositif-search" : $("#dispositif-search").val() },
            function(data) {
                $("#dispositif-effets").empty();
                if (data.length === 0) {
                    $("#dispositif-effets").append("<li>Aucun résultat</li>");
                } else {
                    for (var i = 0; i < data.length; i++) {
                        $("#dispositif-effets").append("<li>" + data[i] + "</li>");
                    }
                }
            },
            "json"
        );
        return false;
    });

    $("#cosmetique-search").keyup(function() {
        if ($(this).val() != "") {
            $.getJSON(
                "autocomplete/cosmetique/" + $(this).val(),
                function(data) {
                    $("#cosmetique-search").autocomplete({
                        source: data
                    });
                }
            );
        }
    });

    $("#cosmetique-form").submit(function(e) {
        e.preventDefault();
        $("#cosmetique-effets").html('<li><img src="/assets/images/wait.gif" height="42" width="42" alt="wait" /></li>');
        $.post(
            "/recherche/cosmetique",
            { "cosmetique-search" : $("#cosmetique-search").val() },
            function(data) {
                $("#cosmetique-effets").empty();
                if (data.length === 0) {
                    $("#cosmetique-effets").append("<li>Aucun résultat</li>");
                } else {
                    for (var i = 0; i < data.length; i++) {
                        $("#cosmetique-effets").append("<li>" + data[i] + "</li>");
                    }
                }
            },
            "json"
        );
        return false;
    });

    $(".ei-search").keyup(function() {
        var input = $(this);
        if ($(this).val() != "") {
            $.getJSON(
                "autocomplete/ei/" + $(this).val(),
                function(data) {
                    input.autocomplete({
                        source: data
                    });
                }
            );
        }
    });

    $("#ei-form").submit(function(e) {
        e.preventDefault();
        $("#ei-effets").html('<li><img src="/assets/images/wait.gif" height="42" width="42" alt="wait" /></li>');
        $.post(
            "/recherche/ei",
            { "ei-search" : $("#ei-search").val() },
            function(data) {
                $("#ei-effets").empty();
                for (var i = 0; i < data.length; i++) {
                    $("#ei-effets").append("<li>" + data[i] + "</li>");
                }
            },
            "json"
        );
        return false;
    });

    $("#substances-search").submit(function(e) {
        e.preventDefault();
        if ($("#substances-search input").val().length !== 0) {
            $("#substances-results tbody").html('<tr><td><img src="/assets/images/wait.gif" height="42" width="42" alt="wait" /></td></tr>');
            $.get(
                "/Substance/recherche/" + $("#substances-search input").val(),
                function(data) {
                    $("#substances-results tbody").empty();
                    for (var i = 0; i < data.length; i++) {
                        $("#substances-results tbody").append("<tr><td>" + data[i] + "</td></tr>");
                    }
                },
                "json"
            );
        } else {
            $("#substances-results tbody").empty();
        }

        return false;
    });

    $("#produits-cosmetiques-search").submit(function(e) {
        e.preventDefault();
        if ($("#produits-cosmetiques-search input").val().length !== 0) {
            $("#produits-cosmetiques-results tbody").html('<tr><td><img src="/assets/images/wait.gif" height="42" width="42" alt="wait" /></td></tr>');
            $.get(
                "/Produit_cosmetique/recherche/" + $("#produits-cosmetiques-search input").val(),
                function(data) {
                    $("#produits-cosmetiques-results tbody").empty();
                    for (var i = 0; i < data.length; i++) {
                        $("#produits-cosmetiques-results tbody").append("<tr><td>" + data[i] + "</td></tr>");
                    }
                },
                "json"
            );
        } else {
            $("#produits-cosmetiques-results tbody").empty();
        }

        return false;
    });

    $("#medicaments-search").submit(function(e) {
        e.preventDefault();
        if ($("#medicaments-search input").val().length !== 0) {
            $("#medicaments-results tbody").html('<tr><td><img src="/assets/images/wait.gif" height="42" width="42" alt="wait" /></td></tr>');
            $.get(
                "/Medicament/recherche/" + $("#medicaments-search input").val(),
                function(data) {
                    $("#medicaments-results tbody").empty();
                    for (var i = 0; i < data.length; i++) {
                        $("#medicaments-results tbody").append("<tr><td>" + data[i] + "</td></tr>");
                    }
                },
                "json"
            );
        } else {
            $("#medicaments-results tbody").empty();
        }

        return false;
    });
});