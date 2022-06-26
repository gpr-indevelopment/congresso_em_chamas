import React from "react";
import { Input, Select, Form, Button, Col, Row } from "antd";
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
      labelCol={{ span: 24 }}
      wrapperCol={{ span: 24 }}
      initialValues={{
        nome: "",
        uf: ""
      }}
    >
    <Row>
      <Col span={12}>
        <Form.Item name="nome"  style={{ margin: "2%", marginTop: "1.3%" }}>
          <Input placeholder="Deputado federal"/>
        </Form.Item>
      </Col>
      <Col span={8}>
      <Form.Item name="uf" style={{ margin: "2%" }}>
        <Select placeholder={"UF"}>
          {estados.map(item => (
            <Option key={item}>{item}</Option>
          ))}
        </Select>
      </Form.Item>
      </Col>
      <Col span={4}>
        <Button type="primary" shape="circle" icon={<SearchOutlined />}  style={{ margin: "2%" }} htmlType="submit"/>
      </Col>
    </Row>
  </Form>
  );
}

export default PoliticianSearch;
