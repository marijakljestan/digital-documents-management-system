import classes from './Registration.module.css';
import React  from 'react';
import { useNavigate } from 'react-router';
import { RegisterCandidateService } from '../../service/RegisterCandidateService';

function RegistrationPage() {

    const navigate = useNavigate();

    function handleCancel() {
        navigate("/");
    }

    function handleSubmit(event) {
        event.preventDefault();

        const formData = new FormData();
        formData.append("firstName",    event.target.firstName.value);
        formData.append("lastName",     event.target.lastName.value);
        formData.append("email",        event.target.email.value);
        formData.append("password",     event.target.password.value);
        formData.append("degree",       event.target.degree.value);
        formData.append("phoneNumber",  event.target.phone.value);
        formData.append("streetName",   event.target.streetName.value);
        formData.append("streetNumber", event.target.streetNumber.value);
        formData.append("city",         event.target.city.value);
        formData.append("country",      event.target.country.value);
        formData.append("cv",           event.target.cv.files[0]);
        formData.append("coverLetter",  event.target.coverLetter.files[0]);

        try {
            RegisterCandidateService.registerCandidate(formData);
        } catch (error) {
            console.error(error);
        }

    }
    
    return(
        <div className={classes.page}>
            <div className={classes.title_wrapper}>
                <h1 className={classes.title}> Register here</h1>
            </div>
            <form onSubmit={(e) => handleSubmit(e)} encType="multipart/form-data" className={classes.form_wrapper}>
                <div className={classes.left_side}>
                    <input className={classes.form_input} required type='text'     id='firstName' placeholder='First name*'      /><br/>
                    <input className={classes.form_input} required type='text'     id='lastName'  placeholder='Last name*'        /><br/>
                    <input className={classes.form_input} required type='email'    id='email'     placeholder='Email*'            /><br/>
                    <input className={classes.form_input} required type='password' id='password'  placeholder='Password*'         /><br/>       
                    <input className={classes.form_input} required type='text'     id='degree'    placeholder='Education degree*' /><br/>   
                    <div className={classes.upload_files}>
                        <p> CV: </p>
                        <input required type='file' id='cv' className={classes.file_upload} />
                    </div> <br/> 
                    <div className={classes.upload_files}>
                        <p> Cover letter: </p>
                        <input required type='file' id='coverLetter' className={classes.file_upload} />
                    </div> <br/>     
                </div>
                <div className={classes.right_side}>
                    <input className={classes.form_input} required type='text'  id='phone'        placeholder='Phone number*'  /><br/>
                    <input className={classes.form_input} required type='text'  id='streetName'   placeholder='Street name*'   /><br/>
                    <input className={classes.form_input} required type='text'  id='streetNumber' placeholder='Street number*' /><br/>
                    <input className={classes.form_input} required type='text'  id='city'         placeholder='City*'          /><br/>
                    <input className={classes.form_input} required type='text'  id='country'      placeholder='Country*'       /><br/>
                    <div className={classes.btn_div}>
                        <button className={classes.cancel_button} onClick={handleCancel}> Cancel</button>
                        <button className={classes.save_button}   type="submit"> Confirm</button> 
                    </div>
                </div>
            </form>
        </div>
    );

}

export default RegistrationPage;