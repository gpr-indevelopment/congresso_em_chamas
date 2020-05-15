function renderExpenses(expenses, element) {
    element.innerHTML = `   <div class="chart-container mr-3 mb-3">
                                <canvas id="chart"></canvas>
                            </div>
                            <div class="border shadow d-flex justify-content-center align-items-center" id="chart-details-container">
                                <small class="text-center w-50">Clique em uma despesa ou data para visualizar seus detalhes.</small>
                            </div>`;
    var expenseData = {
        values: [],
        dates: [],
        monthlyExpenses: []
    }
    buildExpenseData(expenses, expenseData);
    var ctx = document.getElementById("chart").getContext('2d');
    var chart_config = {
        // The type of chart we want to create
        type: 'bar',
        // The data for our dataset
        data: {
            labels: expenseData.dates,
            datasets: [{
                fill: false,
                label: 'Despesas',
                backgroundColor: '#0078ff',
                borderColor: '#0078ff',
                data: expenseData.values,
                lineTension: 0
            }]
        },

        // Configuration options go here
        options: {
            responsive: true,
            maintainAspectRatio: false,
            onClick: function (event, array) {
                let elementIndex = array[0]._index;
                showExpenseDetails(expenseData.monthlyExpenses[elementIndex].expenses);
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

function showExpenseDetails(expenses) {
    let detailsContainer = document.getElementById("chart-details-container");
    detailsContainer.classList.remove("align-items-center");
    detailsContainer.innerHTML = `<ul id="chart-details-list" class="list-group p-1"></ul>`;
    let detailsList = document.getElementById("chart-details-list");
    expenses.forEach(expense => {
        if (expense.documentUrl) {
            detailsList.insertAdjacentHTML("beforeend", `<a href="${expense.documentUrl}" target="_blank" class="list-group-item list-group-item-action rounded-lg shadow mb-2">
                <h6>${expense.provider}</h6>
                <h6 class="font-weight-light">${expense.type}</h6>
                <div class="d-flex align-items-center">
                    <i class="fas fa-money-bill-wave mr-2"></i>
                    <span>R$ ${expense.value.toLocaleString()}</span>
                </div>
                <div class="d-flex align-items-center justify-content-between">
                    <div>
                        <i class="far fa-calendar-alt mr-2"></i>
                        <span>${new Date(expense.date).toLocaleDateString()}</span>
                    </div>
                    <i class="far fa-file-alt mr-2 fa-lg"></i>
                </div>
            </a>`)
        }
        else {
            detailsList.insertAdjacentHTML("beforeend", `<li class="list-group-item rounded-lg shadow mb-2">
                <h6>${expense.provider}</h6>
                <h6 class="font-weight-light">${expense.type}</h6>
                <div class="d-flex align-items-center">
                    <i class="fas fa-money-bill-wave mr-2"></i>
                    <span>R$ ${expense.value.toLocaleString()}</span>
                </div>
                <div class="d-flex align-items-center">
                    <i class="far fa-calendar-alt mr-2"></i>
                    <span>${new Date(expense.date).toLocaleDateString()}</span>
                </div>
            </li>`)
        }
    })
}

function buildExpenseData(expenses, expenseData) {
    expenses.sort((a, b) => a.month - b.month)
    expenses.forEach(expense => {
        let currentMonth = (expense.month < 10) ? "0" + expense.month : expense.month;
        expenseData.values.push(expense.value.toFixed(2));
        expenseData.dates.push(`${currentMonth}/${expense.year}`)
        expenseData.monthlyExpenses.push(expense);
    });
    return expenseData;
}