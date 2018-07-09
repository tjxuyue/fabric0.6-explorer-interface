package com.project.entity;

public class ChaincodeRequest {
	public String jsonrpc;
	public String method;
	public Params params;
	public long id;

	public Params getParams() {
		return params;
	}

	public void setParams(Params params) {
		this.params = params;
	}

	public String getJsonrpc() {
		return jsonrpc;
	}

	public void setJsonrpc(String jsonrpc) {
		this.jsonrpc = jsonrpc;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public class Params {
		public long type;
		public ChaincodeID chaincodeID;
		public CtorMsg ctorMsg;
		public String SecureContext;

		public ChaincodeID getChaincodeID() {
			return chaincodeID;
		}

		public void setChaincodeID(ChaincodeID chaincodeID) {
			this.chaincodeID = chaincodeID;
		}

		public long getType() {
			return type;
		}

		public CtorMsg getCtorMsg() {
			return ctorMsg;
		}

		public void setCtorMsg(CtorMsg ctorMsg) {
			this.ctorMsg = ctorMsg;
		}

		public void setType(long type) {
			this.type = type;
		}

		public String getSecureContext() {
			return SecureContext;
		}

		public void setSecureContext(String SecureContext) {
			this.SecureContext = SecureContext;
		}

	};

	public class ChaincodeID {
		public String path;
		public String name;

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}

	public class CtorMsg {
		public String function;
		public String[] args;

		public String getFunction() {
			return function;
		}

		public void setFunction(String function) {
			this.function = function;
		}

		public String[] getArgs() {
			return args;
		}

		public void setArgs(String[] args) {
			this.args = args;
		}
	}

}
