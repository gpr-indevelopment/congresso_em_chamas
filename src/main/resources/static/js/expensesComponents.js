function renderExpenses(expenses, element, chartType) {
    setupButtons(element);
    var expenseData = {
        values: [],
        dates: [],
        monthlyExpenses: []
    }
    let canvas = document.getElementById("chart");
    if (canvas) {
        canvas.remove();
    }
    element.insertAdjacentHTML('beforeend', `<canvas id="chart"></canvas>`);
    buildExpenseData(expenses, expenseData);
    var ctx = document.getElementById("chart").getContext('2d');
    var chart_config = {
        // The type of chart we want to create
        type: chartType,
        // The data for our dataset
        data: {
            labels: expenseData.dates,
            datasets: [{
                fill: false,
                label: 'Despesas',
                backgroundColor: '#0078ff',
                borderColor: '#0078ff',
                data: expenseData.values,
            }]
        },

        // Configuration options go here
        options: {
            responsive: true,
            maintainAspectRatio: false,
            onClick: function (event, array) {
                if (array.length > 0) {
                    let elementIndex = array[0]._index;
                    showExpenseDetails(expenseData.monthlyExpenses[elementIndex].expenses);
                }
            },
            scales: {
                yAxes: [
                    {
                        ticks: {
                            callback: function (value, index, values) {
                                return "R$ " + value;
                            }
                        }
                    }
                ]
            }
        }
    }
    new Chart(ctx, chart_config);
}

function setupButtons(element) {

    document.getElementById("option1").onclick = () => {
        // Esse ano
        initLoadingState();
        $.ajax({
            url: `${baseUrl}politicians/${politicianId}/monthlyexpenses`,
            type: "get",
            data: {
                years: `${new Date().getFullYear()}`
            },
            success: function (expenses) {
                renderExpenses(expenses, element, "bar");
                clearLoadingState();
            },
        })
    }

    document.getElementById("option2").onclick = () => {
        //Últimos 6 meses
        initLoadingState();
        let currentDate = new Date();
        let currentYear = currentDate.getFullYear();
        let currentMonth = currentDate.getMonth();
        let expensesAggregate = [];
        let numberOfMonths = 6;
        let callsMade = 0;
        for (let i = (numberOfMonths); i >= 0; i--) {
            let pastDate = new Date(currentYear, currentMonth - i, 1);
            $.ajax({
                url: `${baseUrl}politicians/${politicianId}/monthlyexpenses`,
                type: "get",
                data: {
                    months: pastDate.getMonth() + 1,
                    years: pastDate.getFullYear()
                },
                traditional: true,
                success: function (expenses) {
                    expenses.forEach(expense => expensesAggregate.push(expense));
                    callsMade++;
                    if (callsMade == numberOfMonths + 1) {
                        renderExpenses(expensesAggregate, element, "bar");
                        clearLoadingState();
                    }
                },
            })
        }
    }

    document.getElementById("option3").onclick = () => {
        //Legislatura
        initLoadingState();
        $.get(`${baseUrl}legislature`)
            .done(legislature => {
                let requestYears = [];
                let startYear = new Date(legislature.startDate).getFullYear();
                let endYear = new Date(legislature.endDate).getFullYear();
                while (startYear <= endYear) {
                    requestYears.push(startYear);
                    startYear++;
                }
                $.ajax({
                    url: `${baseUrl}politicians/${politicianId}/monthlyexpenses`,
                    type: "get",
                    data: {
                        years: requestYears
                    },
                    traditional: true,
                    success: function (expenses) {
                        renderExpenses(expenses, element, "line");
                        clearLoadingState();
                    },
                })
            })
    }
}

function showExpenseDetails(expenses) {
    let detailsContainer = document.getElementById("chart-details-container");
    detailsContainer.classList.remove("align-items-center");
    detailsContainer.innerHTML = `<ul id="chart-details-list" class="list-group p-1"></ul>`;
    let detailsList = document.getElementById("chart-details-list");
    expenses.sort((a, b) => b.value - a.value);
    expenses.forEach(expense => {
        let formattedDate = expense.yearMonth.split("-")[1] + "/" + expense.yearMonth.split("-")[0];
        if (expense.documentUrl) {
            detailsList.insertAdjacentHTML("beforeend", `<a href="${expense.documentUrl}" target="_blank" class="list-group-item list-group-item-action rounded-lg shadow mb-2 expense-details-card">
                <h6>${expense.provider}</h6>
                <h6 class="font-weight-light">${expense.type}</h6>
                <div class="d-flex align-items-center">
                    <i class="fas fa-money-bill-wave mr-2"></i>
                    <span>R$ ${expense.value.toLocaleString()}</span>
                </div>
                <div class="d-flex align-items-center justify-content-between">
                    <div>
                        <i class="far fa-calendar-alt mr-2"></i>
                        <span>${formattedDate}</span>
                    </div>
                    <i class="far fa-file-alt mr-2 fa-lg"></i>
                </div>
            </a>`)
        }
        else {
            detailsList.insertAdjacentHTML("beforeend", `<li class="list-group-item rounded-lg shadow mb-2 expense-details-card">
                <h6>${expense.provider}</h6>
                <h6 class="font-weight-light">${expense.type}</h6>
                <div class="d-flex align-items-center">
                    <i class="fas fa-money-bill-wave mr-2"></i>
                    <span>R$ ${expense.value.toLocaleString()}</span>
                </div>
                <div class="d-flex align-items-center">
                    <i class="far fa-calendar-alt mr-2"></i>
                    <span>${formattedDate}</span>
                </div>
            </li>`)
        }
    })
}

function buildExpenseData(expenses, expenseData) {
    expenses.sort((a, b) => {
        return new Date(a.yearMonth.split("-")[0], a.yearMonth.split("-")[1]) - new Date(b.yearMonth.split("-")[0], b.yearMonth.split("-")[1]);
    })
    expenses.forEach(expense => {
        let currentYear = expense.yearMonth.split("-")[0];
        let currentMonth = expense.yearMonth.split("-")[1];
        expenseData.values.push(expense.value.toFixed(2));
        expenseData.dates.push(`${currentMonth}/${currentYear}`)
        expenseData.monthlyExpenses.push(expense);
    });
    return expenseData;
}