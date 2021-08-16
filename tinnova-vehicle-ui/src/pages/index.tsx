import { AddIcon, DeleteIcon, EditIcon } from '@chakra-ui/icons';
import { Box, Checkbox, Container, IconButton, Input, Select, Tab, TabList, TabPanel, TabPanels, Tabs, useDisclosure } from '@chakra-ui/react';
import { format, parseISO } from 'date-fns';
import { ptBR } from 'date-fns/locale';
import { GetStaticProps } from 'next';
import Link from 'next/link';
import { useState } from 'react';
import { NovoVeiculoForm } from '../components/NovoVeiculoForm';
import { api } from '../services/api';
import styles from './home.module.scss';

type Veiculo = {
  id: string,
  nome: string;
  marca: string;
  ano: number;
  descricao: string;
  vendido: boolean;
  created: Date;
  updated: Date;
}

type HomeProps = {
  veiculosUltimaSemana: Veiculo[];
  todosVeiculos: Veiculo[];
}

export default function Home({ veiculosUltimaSemana, todosVeiculos }: HomeProps) {
  const { isOpen, onOpen, onClose } = useDisclosure();
  const [veiculosUltimaSemanaList, setVeiculosUltimaSemanaList] = useState<Veiculo[]>(veiculosUltimaSemana as Veiculo[]);

  const handleDelete = (veiculoADeletar: Veiculo) => {
    api.delete(`/veiculos/${veiculoADeletar.id}`);
    setVeiculosUltimaSemanaList(veiculosUltimaSemana.filter(veiculo => veiculo.id = veiculoADeletar.id));
  }

  return (
    <Container p={2} className={styles.homePage}>

      <NovoVeiculoForm isOpen={isOpen} onOpen={onOpen} onClose={onClose} />

      <Box pb={4}>
        <IconButton aria-label="Cadastrar Veículo" variant="outline" icon={<AddIcon />} onClick={onOpen} />
      </Box>

      <Tabs isFitted variant="enclosed">
        <TabList mb="1em">
          <Tab>Essa Semana ({veiculosUltimaSemana.length})</Tab>
          <Tab>Todos</Tab>
        </TabList>
        <TabPanels>
          <TabPanel>
            <section className={styles.ultimosVeiculos}>
              <Checkbox size="sm">Ordenar por década</Checkbox>
              <Box d="flex">
                <Select placeholder="..." width="40%" mr={2} onChange={()=> {}}>
                  <option value="nome">Nome</option>
                  <option value="marca">Marca</option>
                  <option value="ano">Ano</option>
                </Select>
                <Input type="" placeholder="Buscar" onBlur={() => {}}/>
              </Box>

              <Box m={4} mt={8}>
                <ul>
                  {veiculosUltimaSemana.map((veiculo) => {
                    return (
                      <li key={veiculo.id}>
                        <div className={styles.detalhesVeiculo}>
                          <Link href={`/veiculos/${veiculo.id}`}>
                            <a>{veiculo.nome}</a>
                          </Link>
                          
                          <span>{veiculo.marca}</span><br/>
                          <span>{veiculo.created}</span>
                          <span style={{ display: veiculo.vendido ? "hidden" : "inline" }}>disponível</span>

                          <Box d="flex" justifyContent="center">
                            <IconButton aria-label="Editar Veículo" icon={<EditIcon />} variant="unstyled" />
                            <IconButton aria-label="Remover Veículo" icon={<DeleteIcon />} variant="unstyled" color="red" onClick={() => handleDelete(veiculo)} />
                          </Box>
                        </div>
                      </li>
                    )
                  })}
                </ul>
              </Box>
            </section>
          </TabPanel>

          <TabPanel>
            <section className={styles.ultimosVeiculos}>
              <Box m={4} mt={8}>
                <ul>
                  {todosVeiculos.map((veiculo) => {
                    return (
                      <li key={veiculo.id}>
                        <div className={styles.detalhesVeiculo}>
                          <Link href={`/veiculos/${veiculo.id}`}>
                            <a>{veiculo.nome}</a>
                          </Link>

                          <span>{veiculo.created}</span>

                          <span style={{ display: veiculo.vendido ? "hidden" : "inline" }}>disponível</span>
                          {/* <span>{veiculo.updated}</span> */}
                        </div>
                      </li>
                    )
                  })}
                </ul>
              </Box>
            </section>
          </TabPanel>
        </TabPanels>
      </Tabs>

    </Container>
  )
}

export const getStaticProps: GetStaticProps = async () => {
  const { data } = await api.get("veiculos", {
    params: {
      _limit: 12,
      _sort: 'created',
      order: 'desc'
    }
  })

  const todosVeiculos = data.map((veiculo: Veiculo) => {
    return {
      id: veiculo.id,
      nome: veiculo.nome,
      marca: veiculo.marca,
      ano: veiculo.ano,
      descricao: veiculo.descricao,
      created: format(parseISO(veiculo.created.toString()), 'd MMM yy', { locale: ptBR }),
      updated: format(parseISO(veiculo.updated.toString()), 'd MMM yy', { locale: ptBR }),
    };
  })

  console.log("veiculos", todosVeiculos);

  const veiculosUltimaSemana = todosVeiculos.slice(0, 10)

  return {
    props: {
      todosVeiculos,
      veiculosUltimaSemana
    },
    revalidate: 60 * 60 * 8,
  }
}