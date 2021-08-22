import React from 'react'
import Header from './Header';

export default function AdminIndex({body}) {
    return (
        <div className = "container">
            <div className = "">
                <Header/>
            </div>
            <div className = "">
                {body}
            </div>
        </div>
    )
}
