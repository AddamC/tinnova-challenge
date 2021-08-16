import { Box, Button, FormControl, FormLabel, Input, Modal, ModalBody, ModalCloseButton, ModalContent, ModalFooter, ModalHeader, ModalOverlay, NumberDecrementStepper, NumberIncrementStepper, NumberInput, NumberInputField, NumberInputStepper, Textarea } from '@chakra-ui/react';
import { useFormik } from 'formik';
import { useState } from 'react';
import * as yup from 'yup';
import { api } from '../../services/api';

type Veiculo = {
    nome: string;
    marca: string;
    ano: number;
    descricao: string;
}

type VisualizarVeiculoFormProps = {
    isOpen: boolean,
    onOpen: () => void,
    onClose: () => void
}

const validationSchema = yup.object().shape({
    nome: yup.string().required('Preenchimento obrigatório'),
    marca: yup.string().required('Preenchimento obrigatório'),
    descricao: yup.string().required('Preenchimento obrigatório'),
    ano: yup.number().required('Preenchimento obrigatório')
})

export function VisualizarVeiculoForm({ isOpen, onOpen, onClose }: VisualizarVeiculoFormProps) {
    const {
        values,
        errors,
        touched,
        handleBlur,
        handleChange,
        handleSubmit,
        isSubmitting
    } = useFormik({
        onSubmit: async (values, form) => {
            const { data } = await api.post("veiculos", {
                nome: values.nome,
                marca: values.marca,
                descricao: values.descricao,
                ano: values.ano,
            })

            if (data) {
                console.log("dado inserido com sucesso!", data)
            }
        },
        validationSchema,
        initialValues: {
            nome: '',
            marca: '',
            descricao: '',
            ano: 2010
        }
    })

    return (
        <Modal isOpen={isOpen} onClose={onClose}>
            <ModalOverlay />
            <ModalContent>
                <ModalHeader>Adicionar novo veículo</ModalHeader>
                <ModalCloseButton />
                <ModalBody>
                    <Box>
                        <FormControl id='nome' p={4} isRequired isDisabled>
                            <FormLabel>Nome do veículo</FormLabel>
                            <Input size="lg" type="text" placeholder="Etios, C3, HB20..." value={values.nome} onChange={handleChange} />
                        </FormControl>

                        <FormControl id='marca' p={4} isRequired>
                            <FormLabel>Marca</FormLabel>
                            <Input size="lg" type="text" placeholder="Toyota, Citroen, Hyundai" value={values.marca} onChange={handleChange} />
                        </FormControl>

                        <FormControl id='descricao' p={4} isRequired>
                            <FormLabel>Descrição</FormLabel>
                            <Textarea size="lg" placeholder="Detalhe o veículo" value={values.descricao} onChange={handleChange} />
                        </FormControl>

                        <FormControl id='ano' p={4} isRequired>
                            <FormLabel>Ano</FormLabel>
                            <NumberInput size="lg" placeholder="2016, 2010, 1994..." value={values.ano} onChange={handleChange}
                                max={new Date().getFullYear()} min={1970} clampValueOnBlur={true}>
                                <NumberInputField />
                                <NumberInputStepper>
                                    <NumberIncrementStepper />
                                    <NumberDecrementStepper />
                                </NumberInputStepper>
                            </NumberInput>
                        </FormControl>

                    </Box>
                </ModalBody>

                <ModalFooter>
                    <Button colorScheme="blue" mr={3} onClick={() => handleSubmit()} isLoading={isSubmitting}>
                        Salvar
                    </Button>
                    {/* <Button variant="ghost" onClick={}>Cancelar</Button> */}
                </ModalFooter>
            </ModalContent>
        </Modal>
    );
}