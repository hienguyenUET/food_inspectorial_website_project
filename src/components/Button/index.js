//Thiết kế nút bấm
import './Button.css'
import { Link } from 'react-router-dom'

//Class mặc định của nút bấm
const STYLES = ['btn--primary', 'btn--outline']

//Kích thước mặc định của nút bấm
const SIZES = ['btn--medium', 'btn--large', 'btn--extra--large']
export const Button = ({
    children,
    type,
    onClick,
    buttonStyle,
    buttonSize,
    buttonHref,
    buttonDisable
}) => {
    const checkButtonStyle = STYLES.includes(buttonStyle) ? buttonStyle : STYLES[0]
    const checkButtonSize = SIZES.includes(buttonSize) ? buttonSize : SIZES[0]

    return (
        <Link to={buttonHref !== undefined ? `/${buttonHref}` : '#'} className='btn-mobile'>
            <button
                className={`btn ${checkButtonStyle} ${checkButtonSize}`}
                onClick={onClick}
                type={type}
                disabled={buttonDisable}
            >
                {children}
            </button>
        </Link>
    )
}