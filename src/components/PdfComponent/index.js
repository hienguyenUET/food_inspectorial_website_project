import React from 'react'
import ReactToPrint from 'react-to-print'
import { Button } from '../../components/Button'

import DataComponent from '../DataComponent'

class PdfComponent extends React.Component {
    render() {
        return (
            <div>
                <ReactToPrint
                    content={() => this.componentRef}
                    trigger={() => <Button children='In giấy chứng nhận' buttonSize='btn--large' buttonStyle='btn--outline' />}
                />
                <DataComponent ref={(response) => (this.componentRef = response)} />
            </div>
        )
    }

}

export default PdfComponent