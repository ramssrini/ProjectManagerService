pipeline{

	agent{
		docker 
		{
			image 'java:8'
			
		
		}
	}
	environment
	{
		CI = 'true'
	}
	stages{
		stage('Build'){
			echo 'Building......'
			sh './mvnw install dockerfile:build'
			
		}
		stage('Test'){
			echo 'Testing......'
		
		}
	
	}

}