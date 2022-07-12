const prod = {
    url: "https://api.congressoemchamas.com.br",
  };
  const dev = {
    url: "http://develop.congressoemchamas.com.br",
  };
  export const config = process.env.NODE_ENV === "development" ? dev : prod;
  