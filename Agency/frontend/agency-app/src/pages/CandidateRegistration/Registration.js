import classes from './Registration.module.css';
import React  from 'react';
import {useState} from 'react';
import { RegisterCandidateService } from '../../service/RegisterCandidateService';

function RegistrationPage() {

    const [address, setAddress] = useState({streetName: '', streetNumber: '', city: '', country: ''});
    const [candidate, setCandidate] = useState({firstName: '', lastName: '', email : '', password: '', degree: '', address: {}, phoneNumber: '', cv: null, coverLetter: null});

    function handleChange(e) {
        const targetId = e.target.id;
        const targetValue = e.target.value;

        if(targetId === 'firstName') {
            setCandidate(previousState => ({
                ...previousState,
                firstName: targetValue
            }));
        }
        else if(targetId === 'lastName') {
            setCandidate(previousState => ({
                ...previousState,
                lastName: targetValue
            }));
        }

        else if(targetId === 'email') {
            setCandidate(previousState => ({
                ...previousState,
                email: targetValue
            }));
        }

        else if(targetId === 'password') {
            setCandidate(previousState => ({
                ...previousState,
                password: targetValue
            }));
        }

        else if(targetId === 'phone') {
            setCandidate(previousState => ({
                ...previousState,
                phoneNumber: targetValue
            }));
        }

        else if(targetId === 'degree') {
            setCandidate(previousState => ({
                ...previousState,
                degree: targetValue
            }));
        }

        else if(targetId === 'cv') {
            setCandidate(previousState => ({
                ...previousState,
                cv: targetValue
            }));
        }

        else if(targetId === 'coverLetter') {
            setCandidate(previousState => ({
                ...previousState,
                coverLetter: targetValue
            }));
        }

        else if(targetId === 'streetName') {
            setAddress(previousState => ({
                ...previousState,
                streetName: targetValue
            }))
            setCandidate(previousState => ({
                ...previousState,
                address: address
            }));
        }

        else if(targetId === 'streetNumber') {
            setAddress(previousState => ({
                ...previousState,
                streetNumber: targetValue
            }))
            setCandidate(previousState => ({
                ...previousState,
                address: address
            }));
        }

        else if(targetId === 'city') {
            setAddress(previousState => ({
                ...previousState,
                city: targetValue
            }))
            setCandidate(previousState => ({
                ...previousState,
                address: address
            }));
        }

        else if(targetId === 'country') {
            setAddress(previousState => ({
                ...previousState,
                country: targetValue
            }))
            setCandidate(previousState => ({
                ...previousState,
                address: address
            }));
        }
        
    }

    function handleCancel() {

    }

    function handleSubmit(event) {
        console.log(candidate);

        event.preventDefault();
        const formData = new FormData();
  
        // Add form inputs to FormData
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
        
        // Add file input to FormData
        formData.append("cv",          event.target.cv.files[0]);
        formData.append("coverLetter", event.target.coverLetter.files[0]);

        try {
            RegisterCandidateService.registerCandidate(formData);
          } catch (error) {
            console.error(error);
          }

        //RegisterCandidateService.registerCandidate(candidate);
    }
    
    return(
        <div className={classes.page}>
            <div className={classes.title_wrapper}>
                <h1 className={classes.title}> Register here</h1>
            </div>
            <form onSubmit={(e) => handleSubmit(e)} encType="multipart/form-data" className={classes.form_wrapper}>
                <div className={classes.left_side}>
                    <input className={classes.form_input} required type='text'     id='firstName' placeholder='First name*'      onChange={handleChange}/><br/>
                    <input className={classes.form_input} required type='text'     id='lastName' placeholder='Last name*'        onChange={handleChange}/><br/>
                    <input className={classes.form_input} required type='email'    id='email'    placeholder='Email*'            onChange={handleChange}/><br/>
                    <input className={classes.form_input} required type='password' id='password' placeholder='Password*'         onChange={handleChange}/><br/>       
                    <input className={classes.form_input} required type='text'     id='degree'   placeholder='Education degree*' onChange={handleChange}/><br/>   
                    <div className={classes.upload_files}>
                        <p> CV: </p>
                        <input required type='file' id='cv' className={classes.file_upload} onChange={handleChange}/>
                    </div> <br/> 
                    <div className={classes.upload_files}>
                        <p> Cover letter: </p>
                        <input required type='file' id='coverLetter' className={classes.file_upload} onChange={handleChange}/>
                    </div> <br/>     
                </div>
                <div className={classes.right_side}>
                    <input className={classes.form_input} required type='text'  id='phone'        placeholder='Phone number*'  onChange={handleChange}/><br/>
                    <input className={classes.form_input} required type='text'  id='streetName'   placeholder='Street name*'   onChange={handleChange}/><br/>
                    <input className={classes.form_input} required type='text'  id='streetNumber' placeholder='Street number*' onChange={handleChange}/><br/>
                    <input className={classes.form_input} required type='text'  id='city'         placeholder='City*'          onChange={handleChange}/><br/>
                    <input className={classes.form_input} required type='text'  id='country'      placeholder='Country*'       onChange={handleChange}/><br/>
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