function loadPage() {
    buildHtmlTable();
    buildAuthorsOptions();
    buildGenresOptions();
}

async function getBooks() {
    const response = await fetch('api/books')
    return await response.json();
}

async function getAuthors() {
    const response = await fetch('api/authors')
    return await response.json();
}

async function getGenres() {
    const response = await fetch('api/genres')
    return await response.json();
}

async function getUserData() {
    const response = await fetch('api/user')
    return await response.json();
}

async function buildHtmlTable() {
    const books = await getBooks();
    const user = await getUserData();
    const isUserAdmin = user.roles.includes('ROLE_ADMIN');

    $("#excelDataTable").find('#excelData').remove()

    var row$ = $('<tbody id="excelData"/>');
    for (var i = 0; i < books.length; i++) {
        row$.append($('<tr/>'));
        row$.append($('<td/>').html(books[i].id));
        row$.append($('<td/>').html(books[i].title));
        row$.append($('<td/>').html(books[i].author.name));
        row$.append($('<td/>').html(books[i].genre.title));
        row$.append($('<td/>').html(books[i].owner));
        row$.append($('<td/>').html(books[i].comments.length));
        if (isUserAdmin) {
            row$.append($('<td/>').html('<a class="nav-link" href="/edit?id=' + books[i].id + '" href="edit.html">Edit</a>'));
        }
        $("#excelDataTable").append(row$);
    }
}

async function buildAuthorsOptions() {
    const authors = await getAuthors();
    for (let i = 0; i < authors.length; i++) {
        const option$ = $('<option value="' + authors[i].id + '"/>').html(authors[i].name);
        $("#author-input").append(option$);
    }
}

async function buildGenresOptions() {
    const genres = await getGenres();
    for (let i = 0; i < genres.length; i++) {
        const option$ = $('<option value="' + genres[i].id + '"/>').html(genres[i].title);
        $("#genre-input").append(option$);
    }
}

async function addBook() {
    const user = await getUserData();
    const bookDict = {
        "title": document.getElementById('id-input').value,
        "owner": user.username,
        "authorId": parseInt(document.getElementById('author-input').value),
        "genreId": parseInt(document.getElementById('genre-input').value)
    };
    await fetch('api/book', {
        method: 'POST',
        body: JSON.stringify(bookDict),
        headers: {
            'Content-Type': 'application/json'
        }
    })
    await buildHtmlTable();
}

async function logout() {
    await fetch('/logout');
    location.reload();
}