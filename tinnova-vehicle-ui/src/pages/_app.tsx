import { ChakraProvider } from '@chakra-ui/react';
import { AppProps } from 'next/app';
import { Header } from '../components/Header';
import styles from '../styles/app.module.scss';
import '../styles/global.scss';



function MyApp({ Component, pageProps }: AppProps) {
  return (
    <ChakraProvider>
      <div className={styles.wrapper}>
        <main>
          <Header />
          <Component {...pageProps } />
        </main>
      </div>
    </ChakraProvider>
  )
}

export default MyApp