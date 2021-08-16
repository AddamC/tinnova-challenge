import format from 'date-fns/format'
import ptBR from 'date-fns/locale/pt-BR'

import styles from './styles.module.scss'

export function Header() {
    const currentDate = format(new Date(), 'EEEEEE, d MMMM', {
        locale: ptBR
    });
    
    return (
        <header className={styles.headerContainer}>
            <img src="/logo.png" alt="Tinnova car" />
            <p>Gerenciamento de veículos é com a Tinnova Car</p>

            <span>{currentDate}</span>
        </header>
    );
}