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

async function buildHtmlTable() {
    const books = await getBooks();
    $("#excelDataTable").find('#excelData').remove()

    var row$ = $('<tbody id="excelData"/>');
    for (var i = 0; i < books.length; i++) {
        row$.append($('<tr/>'));
        row$.append($('<td/>').html(books[i].id));
        row$.append($('<td/>').html(books[i].title));
        row$.append($('<td/>').html(books[i].author.name));
        row$.append($('<td/>').html(books[i].genre.title));
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
    const bookDict = {
        "title": document.getElementById('id-input').value,
        "authorId": document.getElementById('author-input').value,
        "genreId": document.getElementById('genre-input').value
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

async function editBook() {
    const bookDict = {
        "id": document.getElementById('id-input').value,
        "title": document.getElementById('title-input').value,
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