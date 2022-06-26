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
      onFinish={(inputs) => props.handleSearchSubmit({nome: inputs.nome === undefined ? "" : inputs.nome, uf: inputs.uf === undefined ? "" : inputs.uf})}
      autoComplete="off"
      style={{width: "100%", textAlign: "center"}}
    >
      <Form.Item>
        <Input.Group compact>
          <Form.Item
            name={"uf"}
            noStyle
          >
            <Select placeholder="UF" style={{ width: '30%' }}>
              {estados.map(item => (
                <Option key={item}>{item}</Option>
              ))}
            </Select>
          </Form.Item>
          <Form.Item
            name={"nome"}
            noStyle
          >
            <Input placeholder="Deputado federal" style={{ width: '50%' }}/>
          </Form.Item>
          <Form.Item>
          <Button type="primary" shape="circle" icon={<SearchOutlined />} style={{ marginLeft: 5 }} htmlType="submit"/>
          </Form.Item>
        </Input.Group>
        
      </Form.Item>
  </Form>
  );
}

export default PoliticianSearch;
