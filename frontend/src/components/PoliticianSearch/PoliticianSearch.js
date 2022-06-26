import React from "react";
import { Input, Select, Form, Button } from "antd";
import { SearchOutlined } from '@ant-design/icons';

const { Option } = Select;
const estados = ["AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MS", "MT", 
"PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO"];
    

function PoliticianSearch(props) {

  return (
    <Form
      name="basic"
      onFinish={(inputs) => props.handleSearchSubmit(inputs)}
      autoComplete="off"
      initialValues={{
        nome: "",
        uf: ""
      }}
    >
    <Input
      placeholder="Deputado federal"
      enterButton
      style={{ width: "60%", margin: "1%" }}
      name="nome"
    />
    <Select placeholder={"UF"} style={{ width: "20%", margin: "1%" }} name="uf">
      {estados.map(item => (
        <Option key={item}>{item}</Option>
      ))}
    </Select>
    <Button type="primary" shape="circle" icon={<SearchOutlined />}  style={{ margin: "1%" }} htmlType="submit"/>
  </Form>
  );
}

export default PoliticianSearch;
