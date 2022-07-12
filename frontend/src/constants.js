const prod = {
    url: "http://api.congressoemchamas.com.br/",
  };
  const dev = {
    url: "http://localhost:8080",
  };
  export const config = process.env.NODE_ENV === "development" ? dev : prod;
  